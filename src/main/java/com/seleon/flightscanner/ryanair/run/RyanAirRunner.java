package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.*;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.ryanair.service.FaresProcessorImpl;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.util.List;

import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.AIRPORT_FROM;

public class RyanAirRunner {
    private static final String RYAN_AIR_FLIGHT_DATA_JSON = "ryan_air_" + AIRPORT_FROM + "_" ;
    private static final FaresProcessorImpl faresProcessor = new FaresProcessorImpl();

    public static void main(String[] args) {

        flightDataExample(SearchMode.ONE_WAY);
    }

    private static void flightDataExample(SearchMode searchMode) {
        String fileName = RYAN_AIR_FLIGHT_DATA_JSON + searchMode + "_003.json";
        System.out.println("Read data from file: " + fileName);
        String flightDataJson = FilesUtil.load(FilesUtil.PATH, fileName);
        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);

        List<String> fareLites = faresProcessor.extractFareLites(flightData);
        System.out.println(fareLites.size());
        System.out.println(JsonUtil.listToNewLineString(fareLites));
    }

}
