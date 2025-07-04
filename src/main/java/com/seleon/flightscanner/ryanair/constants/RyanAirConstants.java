package com.seleon.flightscanner.ryanair.constants;

import com.seleon.flightscanner.ryanair.enums.IataCode;

import java.time.format.DateTimeFormatter;

public class RyanAirConstants {

    public static final String RYAN_AIR_ONE_WAY_URL_SAMPLE = String.join("",
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

    public static final String OUTBOUND_JSON_SAMPLE = "{\n" +
            "        \"departureAirport\": {\n" +
            "          \"countryName\": \"Poland\",\n" +
            "          \"iataCode\": \"KRK\",\n" +
            "          \"name\": \"Krakow\",\n" +
            "          \"seoName\": \"krakow\",\n" +
            "          \"city\": {\n" +
            "            \"name\": \"Krakow\",\n" +
            "            \"code\": \"KRAKOW\",\n" +
            "            \"countryCode\": \"pl\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"arrivalAirport\": {\n" +
            "          \"countryName\": \"Italy\",\n" +
            "          \"iataCode\": \"PSR\",\n" +
            "          \"name\": \"Pescara\",\n" +
            "          \"seoName\": \"pescara\",\n" +
            "          \"city\": {\n" +
            "            \"name\": \"Pescara\",\n" +
            "            \"code\": \"PESCARA\",\n" +
            "            \"countryCode\": \"it\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"departureDate\": \"2025-08-08T11:30:00\",\n" +
            "        \"arrivalDate\": \"2025-08-08T13:20:00\",\n" +
            "        \"price\": {\n" +
            "          \"value\": 133.61,\n" +
            "          \"valueMainUnit\": \"133\",\n" +
            "          \"valueFractionalUnit\": \"61\",\n" +
            "          \"currencyCode\": \"PLN\",\n" +
            "          \"currencySymbol\": \"zł\"\n" +
            "        },\n" +
            "        \"flightKey\": \"FR~3045~ ~~KRK~08/08/2025 11:30~PSR~08/08/2025 13:20~~\",\n" +
            "        \"flightNumber\": \"FR3045\",\n" +
            "        \"previousPrice\": null,\n" +
            "        \"priceUpdated\": 1750919208000\n" +
            "      }";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd HH:mm");

    public static IataCode AIRPORT_FROM = IataCode.KRK;

}
