package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class Fare {
    private Outbound outbound;
    private Summary summary;
}