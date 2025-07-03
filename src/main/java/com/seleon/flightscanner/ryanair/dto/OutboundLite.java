package com.seleon.flightscanner.ryanair.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboundLite {
    private String departureAirport;
    private String arrivalIataCode;
    private String arrivalCity;
    private String departureDate;
    private double priceValue;
    private String currencyCode;
}
