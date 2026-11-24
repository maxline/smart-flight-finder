package com.seleon.flightscanner.common;

import com.seleon.flightscanner.nearbyairports.IataCode;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Parameters shared by all FareSources. Provider-specific options live in each adapter.
 * Passengers are fixed to 1 for now.
 */
@Data
@Builder
public class FareSearchRequest {
    private IataCode origin;
    private IataCode destination; // optional

    private LocalDate outboundFrom;
    private LocalDate outboundTo;
    private LocalDate inboundFrom; // only for round trip, optional
    private LocalDate inboundTo;

    private TripType tripType;
    private HolidayType holidayType;

    private Set<DayOfWeek> daysOfWeek;
    private LocalTime departureTimeFrom;
    private LocalTime departureTimeTo;

    @Builder.Default
    private int passengers = 1;

    private String currency;
    private BigDecimal maxPrice;
}
