package com.seleon.flightscanner.ryanair.service;

import com.seleon.flightscanner.ryanair.dto.Airport;
import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.nearbyairports.IataCode;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.seleon.flightscanner.ryanair.service.SampleData.OUTBOUND_JSON_SAMPLE;

public class FaresProcessorImplTest {
    private static final String RYAN_AIR_FARES_JSON = "ryan_air_fares_01.json";

    private final FaresProcessorImpl faresProcessor = new FaresProcessorImpl();

    @Test
    void fileSaverTest() {
        // FileSaver.save("bla-bla-bla11", "bla_bla_txt");
    }

    @Test
    void singleOutboundFromJsonStringTest() {
        Outbound resultOutbound = faresProcessor.singleOutboundJsonFromStringExample(OUTBOUND_JSON_SAMPLE);
        System.out.println("Outbound json parsing test:");
        System.out.println(resultOutbound);
    }

    @Test
    void listFaresFromJsonStringTest() {
        String faresJson = FilesUtil.load("src/test/resources/", RYAN_AIR_FARES_JSON);
        List<Fare> resultFares = faresProcessor.listOutboundJsonFromStringExample(faresJson);

        String printFares = resultFares.stream()
                .map(Fare::toPrettyString)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Fares json parsing test:");
        System.out.println(printFares);
    }

    @Test
    void flightDataFromJsonStringTest() {
        final String ryanAirFlightDataJson = "ryan_air_KRK_ONE_WAY_001.json";

        String flightDataJson = FilesUtil.load("src/test/resources/", ryanAirFlightDataJson);
        System.out.println("FlightData json parsing test:");
        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);
        System.out.println(flightData);
    }

    @Test
    void extractFareLiteTest() {
        final String ryanAirFlightDataJson = "ryan_air_KRK_ONE_WAY_001.json";
        String flightDataJson = FilesUtil.load("src/test/resources/", ryanAirFlightDataJson);
        //todo remove and create FlightData in SampleData class
        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);

        List<String> fareLites = faresProcessor.extractFareLites(flightData);
        System.out.println(JsonUtil.listToNewLineString(fareLites));
    }

    @Test
    void extractAirportsTest() {
        final String ryanAirFlightDataJson = "ryan_air_KRK_ONE_WAY_001.json";
        final IataCode AIRPORT_FROM = IataCode.KRK;

        String flightDataJson = FilesUtil.load("src/test/resources/", ryanAirFlightDataJson);
        //todo remove and create FlightData in SampleData class

        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);

        List<Airport> airports = faresProcessor.extractAirports(flightData);


        System.out.println("Departure from " + AIRPORT_FROM + " - list of arrival Airports:");
        System.out.println(JsonUtil.listToNewLineString(airports));

        List<Map<String, String>> result = airports.stream()
                .map(airport -> Map.of("city", airport.getCity().getName(), "IataCode", airport.getIataCode()))
                .collect(Collectors.toList());
        System.out.println(JsonUtil.listToNewLineString(result));
    }

}