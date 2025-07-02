package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class City {
    private String name;
    private String code;
    private String countryCode;
    private String macCode;  // optional, present in some entries
}
