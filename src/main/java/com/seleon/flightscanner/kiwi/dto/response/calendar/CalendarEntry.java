package com.seleon.flightscanner.kiwi.dto.response.calendar;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalendarEntry {
    private LocalDateTime date;
    private RatedPrice ratedPrice;
}
