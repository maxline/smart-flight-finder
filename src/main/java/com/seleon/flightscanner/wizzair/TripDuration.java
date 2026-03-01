package com.seleon.flightscanner.wizzair;

public enum TripDuration {
    ANYTIME("anytime"),
    WEEKEND("weekend"),
    ONE_WEEK("1 week"),
    ONE_TO_THREE_DAYS("1-3 days"),
    FOUR_TO_EIGHT_DAYS("4-8 days");

    private final String value;

    TripDuration(String value) {
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
