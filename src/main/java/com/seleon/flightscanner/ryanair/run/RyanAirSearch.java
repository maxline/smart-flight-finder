package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.enums.ArrivalAirportCategoryCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.utils.FilesUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.AIRPORT_FROM;

public class RyanAirSearch {

    private static final String FILE_TO_SAVE = "ryan_air_" + AIRPORT_FROM +"_002.json";

    private static final String FARES_URL = String.join("",
            "https://www.ryanair.com/api/farfnd/v4/",
            SearchMode.ONE_WAY_FARES.toString(),
            "?departureAirportIataCode=" + AIRPORT_FROM,
            "&outboundDepartureDateFrom=2025-07-01",
            "&outboundDepartureDateTo=2025-10-31",
            "&market=en-gb",
            "&adultPaxCount=1",
            "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY",
            "&arrivalAirportCategoryCodes=" + ArrivalAirportCategoryCode.BEACH,
            "&outboundDepartureTimeFrom=07:00",
            "&outboundDepartureTimeTo=13:00",
            "&priceValueTo=300",
            "&currency=PLN");

    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(FARES_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                System.out.println("RyanAir fares api from " + AIRPORT_FROM + " call was executed. Response details:");
                System.out.println(response.body());
                FilesUtil.save(response.body(), FILE_TO_SAVE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
