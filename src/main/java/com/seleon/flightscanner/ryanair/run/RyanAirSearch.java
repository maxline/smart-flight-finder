package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.enums.ArrivalAirportCategoryCode;
import com.seleon.flightscanner.utils.FilesUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.AIRPORT_FROM;
import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.SEARCH_MODE;

public class RyanAirSearch {

    private static final String FILE_TO_SAVE = "ryan_air_" + AIRPORT_FROM + "_" + SEARCH_MODE + "_003.json";

    private static final String FARES_URL = String.join("",
            "https://www.ryanair.com/api/farfnd/v4/",
            SEARCH_MODE.getValue(),
            "?departureAirportIataCode=" + AIRPORT_FROM,
            "&arrivalAirportCategoryCodes=" + ArrivalAirportCategoryCode.BEACH,

            "&outboundDepartureDateFrom=2026-03-01",
            "&outboundDepartureDateTo=2026-03-31",
            "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY,SUNDAY",
            "&outboundDepartureTimeFrom=07:00",
            "&outboundDepartureTimeTo=13:00",

            "&durationFrom=1&durationTo=4",
            "&inboundDepartureDateFrom=2026-03-02",
            "&inboundDepartureDateTo=2026-03-31",
            "&inboundDepartureTimeFrom=16:00",
            "&inboundDepartureTimeTo=23:59",

            "&market=en-gb",
            "&adultPaxCount=1",
            "&priceValueTo=600",
            "&currency=PLN");

    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(FARES_URL))
                    .GET()
                    .build();

            // todo warning httpclient used without try with resources statement
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                System.out.println("RyanAir fares api from " + AIRPORT_FROM + " " + SEARCH_MODE.getValue() + " call was executed. Response details:");
                System.out.println(response.body());
                FilesUtil.save(response.body(),FilesUtil.PATH_RYAN, FILE_TO_SAVE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
