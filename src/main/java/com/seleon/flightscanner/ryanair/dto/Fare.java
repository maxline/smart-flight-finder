package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class Fare {
    private Outbound outbound;
    private Summary summary;

    public String toPrettyString() {
        return "Fare{\n" +
                "  outbound=" + outbound +
                ",\n  summary=" + summary +
                "\n}";
    }
}