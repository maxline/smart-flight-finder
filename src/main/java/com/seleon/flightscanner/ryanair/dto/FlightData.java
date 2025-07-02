package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

import java.util.List;

@Data
public class FlightData {
    private String arrivalAirportCategories;
    private List<Fare> fares;
    private String nextPage;
    private int size;
}
