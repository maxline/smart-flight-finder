package com.seleon.flightscanner.wizzair;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WizzSearchParameters {
    // Payload fields
    private boolean isReturnFlight;
    private List<String> departureStations;
    private List<String> arrivalStations;
    private List<String> stdPlan; // months
    private TripDuration tripDuration;
    private Integer pax;
    private DateFilterType dateFilterType;
    private String departureDate;
    private String returnDate;

/*
    String payloadMarchFlexible = "{\"arrivalStations\":null," +
            "\"departureStations\":[\"WRO\"]," +
            "\"tripDuration\":\"anytime\"," +
            "\"isReturnFlight\":false," +
            "\"stdPlan\":[\"2026-03\"]," +
            "\"pax\":1," +
            "\"dateFilterType\":\"Flexible\"," +
            "\"departureDate\":null," +
            "\"returnDate\":null}";


    String payloadWeekend = "{\"arrivalStations\":null," +
            "\"departureStations\":[\"WRO\"]," +
            "\"tripDuration\":\"weekend\"," +
            "\"isReturnFlight\":true," +
            "\"stdPlan\":null," +
            "\"pax\":1," +
            "\"dateFilterType\":\"Flexible\"," +
            "\"departureDate\":null," +
            "\"returnDate\":null}";


    String payload1Week = "{\"arrivalStations\":null," +
            "\"departureStations\":[\"WRO\"]," +
            "\"tripDuration\":\"1 week\"," +
            "\"isReturnFlight\":true," +
            "\"stdPlan\":[\"2026-04\",\"2026-05\"]," +
            "\"pax\":1," +
            "\"dateFilterType\":\"Flexible\"," +
            "\"departureDate\":null," +
            "\"returnDate\":null}";

    String payloadS1_3Days = "{\"arrivalStations\":null," +
            "\"departureStations\":[\"WRO\"]," +
            "\"tripDuration\":\"1-3 days\"," +
            "\"isReturnFlight\":true," +
            "\"stdPlan\":null," +
            "\"pax\":1," +
            "\"dateFilterType\":\"Flexible\"," +
            "\"departureDate\":null," +
            "\"returnDate\":null}";

    String payloadReturn = "{\"arrivalStations\":null,\"departureStations\":[\"WRO\"]" +
            ",\"tripDuration\":\"anytime\"" +
            ",\"isReturnFlight\":true" +
            ",\"stdPlan\":null,\"pax\":1" +
            ",\"dateFilterType\":\"Exact\"" +
            ",\"departureDate\":\"2026-03-05\"" +
            ",\"returnDate\":\"2026-03-19\"}";

 */
}