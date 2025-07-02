package com.seleon.flightscanner.ryanair.enums;

public enum ArrivalAirportCategoryCode {
    BEACH("SEA"),
    CIY_BREAK("CTY"),
    FAMILY("FAM"),
    NIGHT_LIFE("NIT"),
    OUTDOOR("OUT");


    private final String value;

    ArrivalAirportCategoryCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


}