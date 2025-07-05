package com.seleon.flightscanner.ryanair.dto;

import lombok.Data;

@Data
public class Fare {
    private Outbound outbound;
    private Inbound inbound;
    private Summary summary;

    public String toPrettyString() {
        String pretty = "Fare{\n  outbound=" + outbound;

        if (inbound != null) {
            pretty = pretty + ", \n  inbound=" + inbound;
        }
        pretty = pretty + ",\n  summary=" + summary + "\n}";
        return pretty;
    }
}