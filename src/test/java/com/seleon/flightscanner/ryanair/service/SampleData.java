package com.seleon.flightscanner.ryanair.service;

public class SampleData {
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

}