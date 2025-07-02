package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.Fare;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.util.List;

import static com.seleon.flightscanner.ryanair.constants.RyanAirConstants.OUTBOUND_JSON_SAMPLE;

public class RyanAirProcessor {

    private static final String RYAN_AIR_FARES_JSON = "ryan_air_fares_01.json";
    private static final String RYAN_AIR_FLIGHT_DATA_JSON = "ryan_air_002.json";

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
    }
}
