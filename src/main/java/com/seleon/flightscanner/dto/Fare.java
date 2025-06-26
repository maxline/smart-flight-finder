package com.seleon.flightscanner.dto;

import lombok.Data;

@Data
public class Fare {
    private Outbound outbound;
    private Summary summary;
}