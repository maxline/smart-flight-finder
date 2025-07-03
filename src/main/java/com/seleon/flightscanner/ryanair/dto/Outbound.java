package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Outbound {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Price price;
    private String flightKey;
    private String flightNumber;
    private Object previousPrice;
    private long priceUpdated;
}
