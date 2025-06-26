package com.seleon.flightscanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionExample {

    public static void main(String[] args) {
        try {
            String url = String.join("",
                    "https://www.ryanair.com/api/farfnd/v4/oneWayFares",
                    "?departureAirportIataCode=KRK",
                    "&outboundDepartureDateFrom=2025-07-01",
                    "&market=en-gb",
                    "&adultPaxCount=1",
                    "&outboundDepartureDateTo=2025-08-31",
                    "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY",
                    "&arrivalAirportCategoryCodes=SEA",
                    "&outboundDepartureTimeFrom=07:00",
                    "&outboundDepartureTimeTo=13:00",
                    "&priceValueTo=300",
                    "&currency=PLN");

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Ответ от API:");

            if (response != null) {
                System.out.println(response.toString());

                FileSaver.save(response.toString(), "http_url_con_02.json");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
