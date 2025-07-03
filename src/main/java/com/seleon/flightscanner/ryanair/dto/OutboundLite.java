package com.seleon.flightscanner.ryanair.dto;

import com.seleon.flightscanner.ryanair.constants.RyanAirConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
@AllArgsConstructor
public class OutboundLite {
    private String departureAirport;
    private String arrivalIataCode;
    private String arrivalCity;
    private LocalDateTime departureDate;
    private double priceValue;
    private String currencyCode;

    /**
     * @return {'KRK - CTA', flyAt='OCT-30 10:55 Thu', 119.0 PLN, Catania}
     */
    public String toBrief() {
        String dayOfWeek = departureDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String flyAt = departureDate.format(RyanAirConstants.TIME_FORMATTER).toUpperCase() + " " + dayOfWeek;

        return "{" +
                '\'' + departureAirport +
                " - " + arrivalIataCode + '\'' +
                ", flyAt='" + flyAt + '\'' +
                ", " + priceValue + " " + currencyCode +
                ", " + arrivalCity +
                '}';
    }
}
