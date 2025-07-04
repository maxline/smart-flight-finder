package com.seleon.flightscanner.ryanair.constants;

import com.seleon.flightscanner.ryanair.enums.IataCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;

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

    //todo move to unit tests
    public static final String OUTBOUND_JSON_SAMPLE = """
            {
                    "departureAirport": {
                      "countryName": "Poland",
                      "iataCode": "KRK",
                      "name": "Krakow",
                      "seoName": "krakow",
                      "city": {
                        "name": "Krakow",
                        "code": "KRAKOW",
                        "countryCode": "pl"
                      }
                    },
                    "arrivalAirport": {
                      "countryName": "Italy",
                      "iataCode": "PSR",
                      "name": "Pescara",
                      "seoName": "pescara",
                      "city": {
                        "name": "Pescara",
                        "code": "PESCARA",
                        "countryCode": "it"
                      }
                    },
                    "departureDate": "2025-08-08T11:30:00",
                    "arrivalDate": "2025-08-08T13:20:00",
                    "price": {
                      "value": 133.61,
                      "valueMainUnit": "133",
                      "valueFractionalUnit": "61",
                      "currencyCode": "PLN",
                      "currencySymbol": "zł"
                    },
                    "flightKey": "FR~3045~ ~~KRK~08/08/2025 11:30~PSR~08/08/2025 13:20~~",
                    "flightNumber": "FR3045",
                    "previousPrice": null,
                    "priceUpdated": 1750919208000
                  }""";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd HH:mm");

    public static IataCode AIRPORT_FROM = IataCode.KRK;
    public static SearchMode SEARCH_MODE = SearchMode.ONE_WAY;

}
