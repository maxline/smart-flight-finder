package com.seleon.flightscanner.kiwi.dto.response.calendar;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryPricesCalendar {
    private String __typename;
    private List<CalendarEntry> calendar;
}
