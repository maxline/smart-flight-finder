package com.seleon.flightscanner.ryanair.enums;

public enum ArrivalCategory {
    BEACH("SEA"),
    CITY_BREAK("CTY"),
    FAMILY("FAM"),
    NIGHT_LIFE("NIT"),
    OUTDOOR("OUT");


    private final String value;

    ArrivalCategory(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


}