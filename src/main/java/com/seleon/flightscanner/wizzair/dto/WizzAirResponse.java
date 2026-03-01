package com.seleon.flightscanner.wizzair.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WizzAirResponse {
    private List<WizzAirFlightItem> items;

    public WizzAirResponse() {
    }

    public List<WizzAirFlightItem> getItems() {
        return items;
    }

    public void setItems(List<WizzAirFlightItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "WizzAirResponse{" +
                "items=" + items +
                '}';
    }
}
