package com.seleon.flightscanner.ryanair.enums;

import lombok.Getter;

@Getter
public enum SearchMode {
    ONE_WAY("oneWayFares"),
    ROUND("roundTripFares");

    private final String value;

    SearchMode(String value) {
        this.value = value;
    }

}