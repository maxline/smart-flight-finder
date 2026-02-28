package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.SearchParameters;
import com.seleon.flightscanner.ryanair.enums.ArrivalCategory;
import com.seleon.flightscanner.ryanair.enums.IataCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.ryanair.enums.SortBy;
import com.seleon.flightscanner.ryanair.service.FaresProcessorImpl;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;


public class RyanAirSearch {

    private static final SearchMode SEARCH_MODE = SearchMode.ROUND; // SearchMode.ROUND; SearchMode.ONE_WAY;
    private static final ArrivalCategory TYPE_OF_TRIP = ArrivalCategory.BEACH;  //BEACH: CITY_BREAK; OUTDOOR
    private static final SortBy SORT_BY = SortBy.PRICE; // SortBy.DATE_FROM, SortBy.PRICE, SortBy.DAY_OF_WEEK
    private static final Integer PRICE_VALUE_TO = 600;
    private static final String CURRENCY = "PLN";

    private static final String DATE_FROM = "2026-04-01";
    private static final String DATE_TO = "2026-04-30";
    private static final String DEPARTURE_TIME_FROM = "05:45";
    private static final String DEPARTURE_TIME_TO = "13:00";
    private static final String DAYS_OF_WEEK = "FRIDAY,SATURDAY,SUNDAY";

    private static final Integer DURATION_FROM = 1;
    private static final Integer DURATION_TO = 4;
    private static final String INBOUND_DEPARTURE_TIME_FROM = "16:00";
    private static final String INBOUND_DEPARTURE_TIME_TO = "23:59";

    private static String urlCreator(SearchParameters searchParameters) {
        return String.join("",
                "https://www.ryanair.com/api/farfnd/v4/",
                searchParameters.getSearchMode().getValue(),
                "?departureAirportIataCode=" + searchParameters.getAirportFrom(),
                "&arrivalAirportCategoryCodes=" + searchParameters.getArrivalCategory(),

                "&outboundDepartureDateFrom=" + searchParameters.getDateFrom(),
                "&outboundDepartureDateTo=" + searchParameters.getDateTo(),
                "&outboundDepartureDaysOfWeek=" + searchParameters.getDaysOfWeek(),
                "&outboundDepartureTimeFrom=" + searchParameters.getDepartureTimeFrom(),
                "&outboundDepartureTimeTo=" + searchParameters.getDepartureTimeTo(),

                "&durationFrom=" + searchParameters.getDurationFrom() + "&durationTo=" + searchParameters.getDurationTo(),
                "&inboundDepartureDateFrom=" + searchParameters.getInboundDepartureDateFrom(),
                "&inboundDepartureDateTo=" + searchParameters.getInboundDepartureDateTo(),
                "&inboundDepartureTimeFrom=" + searchParameters.getInboundDepartureTimeFrom(),
                "&inboundDepartureTimeTo=" + searchParameters.getInboundDepartureTimeTo(),

                "&priceValueTo=" + searchParameters.getPriceValueTo(),
                "&currency=" + searchParameters.getCurrency(),
                "&adultPaxCount=1",
                "&market=en-gb"
        );
    }

    public static void main(String[] args) {
        try {
             //fareFind(IataCode.WAW);
             fareFind(IataCode.KRK);
            //fareFind(IataCode.VIE);
            fareFind(IataCode.WRO);
            //fareFind(IataCode.KTW);
            //fareFind(IataCode.POZ);
            //fareFind(IataCode.RZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fareFind(IataCode airportFrom) throws URISyntaxException, IOException, InterruptedException {
        // Calculate return dates automatically based on departure dates and duration
        String calculatedInboundDateFrom = LocalDate.parse(DATE_FROM).plusDays(DURATION_FROM).toString();
        String calculatedInboundDateTo = LocalDate.parse(DATE_TO).plusDays(DURATION_TO).toString();

        SearchParameters searchParameters = SearchParameters.builder()
                .dateFrom(DATE_FROM)
                .dateTo(DATE_TO)
                .airportFrom(airportFrom.name())
                .arrivalCategory(TYPE_OF_TRIP)
                .searchMode(SEARCH_MODE)
                .departureTimeFrom(DEPARTURE_TIME_FROM)
                .departureTimeTo(DEPARTURE_TIME_TO)
                .daysOfWeek(DAYS_OF_WEEK)
                .durationFrom(DURATION_FROM)
                .durationTo(DURATION_TO)
                .inboundDepartureDateFrom(calculatedInboundDateFrom)
                .inboundDepartureDateTo(calculatedInboundDateTo)
                .inboundDepartureTimeFrom(INBOUND_DEPARTURE_TIME_FROM)
                .inboundDepartureTimeTo(INBOUND_DEPARTURE_TIME_TO)
                .priceValueTo(PRICE_VALUE_TO)
                .currency(CURRENCY)
                .build();

        String faresUrl = urlCreator(searchParameters);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(faresUrl))
                .GET()
                .build();

        // todo warning httpclient used without try with resources statement
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null) {
            System.out.println("Date from: " + DATE_FROM + ", Date To: " + DATE_TO);

            System.out.println(searchParameters);
            //System.out.println("RyanAir fares api from " + AIRPORT_FROM + " " + SEARCH_MODE.getValue() + " call was executed. Response details:");
            //System.out.println(response.body());

            final String fileToSave = "ryan_air_" + airportFrom.name() + "_" + SEARCH_MODE + "_003.json";
            FilesUtil.save(response.body(),FilesUtil.PATH_RYAN, fileToSave);
            printFoundFlights(response.body(), airportFrom);

        }
    }

    private static void printFoundFlights(String flightDataJson, IataCode airportFrom) {
        System.out.println("\nDate from: " + DATE_FROM + ", Date To: " + DATE_TO);
        System.out.println(airportFrom + " " + SEARCH_MODE.getValue() + " Response details:");
        System.out.println("Sorted by: " + SORT_BY);

        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);
        List<String> fareLites = new FaresProcessorImpl().extractFareLites(flightData, SORT_BY);

        System.out.println(JsonUtil.listToNewLineString(fareLites));
        System.out.println(fareLites.size());
    }
}
