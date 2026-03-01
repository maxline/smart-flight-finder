package com.seleon.flightscanner.wizzair.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private String currencyCode;
    private String departureStation;
    private String arrivalStation;
    private String arrivalStationMAC;
    private String std; // Standard Time of Departure
    private Price regularOriginalPrice;
    private Price regularPrice;
    private Price wdcOriginalPrice;
    private Price wdcPrice;
    private Price pastPrice;
    private Price wdcPastPrice;
    private Integer flightDurationMinutes;

    public Flight() {
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getArrivalStationMAC() {
        return arrivalStationMAC;
    }

    public void setArrivalStationMAC(String arrivalStationMAC) {
        this.arrivalStationMAC = arrivalStationMAC;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public Price getRegularOriginalPrice() {
        return regularOriginalPrice;
    }

    public void setRegularOriginalPrice(Price regularOriginalPrice) {
        this.regularOriginalPrice = regularOriginalPrice;
    }

    public Price getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Price regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Price getWdcOriginalPrice() {
        return wdcOriginalPrice;
    }

    public void setWdcOriginalPrice(Price wdcOriginalPrice) {
        this.wdcOriginalPrice = wdcOriginalPrice;
    }

    public Price getWdcPrice() {
        return wdcPrice;
    }

    public void setWdcPrice(Price wdcPrice) {
        this.wdcPrice = wdcPrice;
    }

    public Price getPastPrice() {
        return pastPrice;
    }

    public void setPastPrice(Price pastPrice) {
        this.pastPrice = pastPrice;
    }

    public Price getWdcPastPrice() {
        return wdcPastPrice;
    }

    public void setWdcPastPrice(Price wdcPastPrice) {
        this.wdcPastPrice = wdcPastPrice;
    }

    public Integer getFlightDurationMinutes() {
        return flightDurationMinutes;
    }

    public void setFlightDurationMinutes(Integer flightDurationMinutes) {
        this.flightDurationMinutes = flightDurationMinutes;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "currencyCode='" + currencyCode + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", arrivalStationMAC='" + arrivalStationMAC + '\'' +
                ", std='" + std + '\'' +
                ", regularOriginalPrice=" + regularOriginalPrice +
                ", regularPrice=" + regularPrice +
                ", wdcOriginalPrice=" + wdcOriginalPrice +
                ", wdcPrice=" + wdcPrice +
                ", pastPrice=" + pastPrice +
                ", wdcPastPrice=" + wdcPastPrice +
                ", flightDurationMinutes=" + flightDurationMinutes +
                '}';
    }
}
