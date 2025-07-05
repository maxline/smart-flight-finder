package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class Summary {
    private Price price;
    private Price previousPrice;
    private boolean newRoute;
    private int tripDurationDays;
}