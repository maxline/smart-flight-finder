package com.seleon.flightscanner.ryanair.dto;

import com.seleon.flightscanner.ryanair.enums.ArrivalCategory;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchParameters {
    private String dateFrom;
    private String dateTo;
    private String airportFrom;
    private ArrivalCategory arrivalCategory;
    private SearchMode searchMode;
    private String departureTimeFrom;
    private String departureTimeTo;
    private String daysOfWeek;

    // return
    private Integer durationFrom;
    private Integer durationTo;
    private String inboundDepartureDateFrom;
    private String inboundDepartureDateTo;
    private String inboundDepartureTimeFrom;
    private String inboundDepartureTimeTo;

    // price and currency
    private Integer priceValueTo;
    private String currency;
}
