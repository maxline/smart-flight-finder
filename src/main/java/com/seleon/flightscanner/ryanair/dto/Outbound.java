package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class Outbound {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String departureDate; //todo format to local date? if needed
    private String arrivalDate;
    private Price price;
    private String flightKey;
    private String flightNumber;
    private Object previousPrice;
    private long priceUpdated;
}
