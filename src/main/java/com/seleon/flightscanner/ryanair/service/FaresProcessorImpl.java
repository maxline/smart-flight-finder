package com.seleon.flightscanner.ryanair.service;

import com.seleon.flightscanner.ryanair.dto.Airport;
import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.ryanair.dto.FareLite;
import com.seleon.flightscanner.ryanair.enums.SortBy;
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
        return extractFareLites(flightData, SortBy.DATE_FROM);
    }

    public List<String> extractFareLites(FlightData flightData, SortBy sortBy) {
        Comparator<FareLite> comparator;

        switch (sortBy) {
            case PRICE:
                // Sort by total price (outbound + inbound if exists)
                comparator = Comparator.comparingDouble(fare ->
                    fare.getOutboundPriceValue() +
                    (fare.getInboundPriceValue() > 0 ? fare.getInboundPriceValue() : 0)
                );
                break;
            case DAY_OF_WEEK:
                // Sort by day of week (Monday=1, Sunday=7)
                comparator = Comparator.comparingInt(fare ->
                    fare.getOutboundDepartureDate().getDayOfWeek().getValue()
                );
                break;
            case DATE_FROM:
            default:
                // Sort by departure date
                comparator = Comparator.comparing(FareLite::getOutboundDepartureDate);
                break;
        }

        return flightData.getFares().stream()
                .map(fare -> new FareLite(fare.getOutbound(), fare.getInbound()))
                .sorted(comparator)
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
