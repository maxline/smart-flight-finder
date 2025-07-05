package com.seleon.flightscanner.ryanair.service;

import com.seleon.flightscanner.ryanair.dto.Airport;
import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.ryanair.dto.OutboundLite;
import com.seleon.flightscanner.utils.JsonUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FaresProcessorImpl {

    public Outbound singleOutboundJsonFromStringExample(String outboundJson) {
        Outbound outboundResult = JsonUtil.jsonToObject(outboundJson, Outbound.class);
        return outboundResult;
    }

    public List<Fare> listOutboundJsonFromStringExample(String faresJson) {
        return JsonUtil.jsonToList(faresJson, Fare.class);
    }

    public List<String> extractOutboundLites(FlightData flightData) {
        List<String> outboundLites = flightData.getFares().stream()
                .map(Fare::getOutbound)
                .filter(Objects::nonNull)
                .map(outbound -> new OutboundLite(
                        outbound.getDepartureAirport().getIataCode(),
                        outbound.getArrivalAirport().getIataCode(),
                        outbound.getArrivalAirport().getCity().getName(),
                        outbound.getDepartureDate(),
                        outbound.getPrice().getValue(),
                        outbound.getPrice().getCurrencyCode()))
                .sorted(Comparator.comparing(OutboundLite::getDepartureDate))
                .map(OutboundLite::toBrief)
                .collect(Collectors.toList());
        return outboundLites;
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
