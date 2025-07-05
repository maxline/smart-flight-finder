package com.seleon.flightscanner.ryanair.service;

import com.seleon.flightscanner.ryanair.dto.Airport;
import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.ryanair.dto.FareLite;
import com.seleon.flightscanner.utils.JsonUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FaresProcessorImpl {

    public Outbound singleOutboundJsonFromStringExample(String outboundJson) {
        return JsonUtil.jsonToObject(outboundJson, Outbound.class);
    }

    public List<Fare> listOutboundJsonFromStringExample(String faresJson) {
        return JsonUtil.jsonToList(faresJson, Fare.class);
    }

    public List<String> extractFareLites(FlightData flightData) {
        return flightData.getFares().stream()
                .map(fare -> new FareLite(fare.getOutbound(), fare.getInbound()))
                .sorted(Comparator.comparing(FareLite::getOutboundDepartureDate))
                .map(FareLite::toBrief)
                .collect(Collectors.toList());
    }

    public List<Airport> extractAirports(FlightData flightData) {
        return flightData.getFares().stream()
                .map(Fare::getOutbound)
                .filter(Objects::nonNull)
                .map(Outbound::getArrivalAirport)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
