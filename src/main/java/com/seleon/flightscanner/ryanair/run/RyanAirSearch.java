package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.enums.ArrivalAirportCategoryCode;
import com.seleon.flightscanner.ryanair.enums.IataCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.ryanair.service.FaresProcessorImpl;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.seleon.flightscanner.ryanair.enums.ArrivalAirportCategoryCode.BEACH;
import static com.seleon.flightscanner.ryanair.enums.ArrivalAirportCategoryCode.CIY_BREAK;

public class RyanAirSearch {
    //public static SearchMode SEARCH_MODE = SearchMode.ROUND;
    public static SearchMode SEARCH_MODE = SearchMode.ONE_WAY;
    public static ArrivalAirportCategoryCode TYPE_OF_TRIP = BEACH;

    private static final String DATE_FROM = "2026-03-01";
    private static final String DEPARTURE_TIME_FROM = "05:45";
    private static final String DEPARTURE_TIME_TO = "13:00";
    private static final String DATE_TO = "2026-03-31";


    private static String urlCreator(String dateFrom, String dateTo, String airportFrom, ArrivalAirportCategoryCode arrivalCategory) {
        return String.join("",
                "https://www.ryanair.com/api/farfnd/v4/",
                SEARCH_MODE.getValue(),
                "?departureAirportIataCode=" + airportFrom,
                "&arrivalAirportCategoryCodes=" + arrivalCategory,

                "&outboundDepartureDateFrom=" + dateFrom,
                "&outboundDepartureDateTo=" + dateTo,
                "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY,SUNDAY",
                "&outboundDepartureTimeFrom=" + DEPARTURE_TIME_FROM,
                "&outboundDepartureTimeTo=" + DEPARTURE_TIME_TO,

                "&durationFrom=1&durationTo=4",
                "&inboundDepartureDateFrom=2026-03-02",
                "&inboundDepartureDateTo=2026-03-31",
                "&inboundDepartureTimeFrom=16:00",
                "&inboundDepartureTimeTo=23:59",

                "&market=en-gb",
                "&adultPaxCount=1",
                "&priceValueTo=300",
                "&currency=PLN");
    }

    public static void main(String[] args) {
        try {
             //fareFind(IataCode.KRK) ;
//            fareFind(IataCode.VIE) ;
            fareFind(IataCode.WRO) ;
            //fareFind(IataCode.KTW) ;
            //fareFind(IataCode.RZE) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fareFind(IataCode airportFrom) throws URISyntaxException, IOException, InterruptedException {
        String faresUrl = urlCreator(DATE_FROM, DATE_TO, airportFrom.name(), TYPE_OF_TRIP); //todo refactor to easy put search parameters in one place, crate a smart parameter filler

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

        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);
        List<String> fareLites = new FaresProcessorImpl().extractFareLites(flightData);

        System.out.println(JsonUtil.listToNewLineString(fareLites));
        System.out.println(fareLites.size());
    }
}
