package com.seleon.flightscanner.ryanair.enums;

public enum SearchMode {
    ONE_WAY_FARES("oneWayFares"),
    ROUND_TRI_FARES("roundTripFares");

    private final String value;

    SearchMode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}