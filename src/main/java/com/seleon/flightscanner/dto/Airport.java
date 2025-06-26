package com.seleon.flightscanner.dto;

import lombok.Data;

@Data
public class Airport {
    private String countryName;
    private String iataCode;
    private String name;
    private String seoName;
    private City city;
}