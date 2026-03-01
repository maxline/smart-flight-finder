package com.seleon.flightscanner.wizzair;

public enum DateFilterType {
    FLEXIBLE("Flexible"),
    EXACT("Exact");

    private final String value;

    DateFilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
