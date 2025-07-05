package com.seleon.flightscanner.ryanair.service;

import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;

import java.util.List;

public interface FaresProcessor {
    Outbound singleOutboundJsonFromStringExample(String outboundJson);

    List<Fare> listOutboundJsonFromFileExample(String faresJson);

    List<String> extractOutboundLites(FlightData flightData);
}
