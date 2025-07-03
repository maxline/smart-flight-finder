package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.Airport;
import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.ryanair.dto.OutboundLite;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.OUTBOUND_JSON_SAMPLE;

public class RyanAirProcessor {

    private static final String RYAN_AIR_FARES_JSON = "ryan_air_fares_01.json";
    private static final String RYAN_AIR_FLIGHT_DATA_JSON = "ryan_air_003.json";

    public static void main(String[] args) {
        //FileSaver.save("bla-bla-bla11", "bla_bla_txt");

        Outbound outboundResult = JsonUtil.jsonToObject(OUTBOUND_JSON_SAMPLE, Outbound.class);
        System.out.println("Outbound json parsing test:");
        System.out.println(outboundResult);

        String load = FilesUtil.load(RYAN_AIR_FARES_JSON);

        List<Fare> fares = JsonUtil.jsonToList(load);
        System.out.println("Fares json parsing test:");
        System.out.println(fares);

        load = FilesUtil.load(RYAN_AIR_FLIGHT_DATA_JSON);

        FlightData flightData = JsonUtil.jsonToObject(load, FlightData.class);
        System.out.println("FlightData json parsing test:");
        System.out.println(flightData);

        List<Airport> airports = flightData.getFares().stream()
                .map(Fare::getOutbound)
                .filter(Objects::nonNull)
                .map(Outbound::getArrivalAirport)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println("Departure from KRK - list of arrival Airports:");
        System.out.println(airports);

        List<Map<String, String>> result = airports.stream()
                .map(airport -> Map.of("city", airport.getCity().getName(), "IataCode", airport.getIataCode()))
                .collect(Collectors.toList());
        System.out.println(result);

        List<OutboundLite> outboundLites = flightData.getFares().stream()
                .map(Fare::getOutbound)
                .filter(Objects::nonNull)
                .map(outbound -> new OutboundLite(
                        outbound.getDepartureAirport().getIataCode(),
                        outbound.getArrivalAirport().getIataCode(),
                        outbound.getArrivalAirport().getCity().getName(),
                        outbound.getDepartureDate(),
                        outbound.getPrice().getValue(),
                        outbound.getPrice().getCurrencyCode()))
                .collect(Collectors.toList());

        System.out.println(JsonUtil.listToNewLineString(outboundLites));

    }
}
