package com.seleon.flightscanner.dto;

import lombok.Data;

@Data
public class Price {
    private double value;
    private String valueMainUnit;
    private String valueFractionalUnit;
    private String currencyCode;
    private String currencySymbol;
}
