package com.seleon.flightscanner.dto;

import lombok.Data;

@Data
public class Summary {
    private Price price;
    private Price previousPrice;
    private boolean newRoute;
}