package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Inbound {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Price price;
    private String flightKey;
    private String flightNumber;
    private Object previousPrice;
    private Object priceUpdated;
}