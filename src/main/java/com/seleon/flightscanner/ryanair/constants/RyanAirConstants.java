package com.seleon.flightscanner.ryanair.constants;

import com.seleon.flightscanner.ryanair.enums.IataCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;

import java.time.format.DateTimeFormatter;

public class RyanAirConstants {

    public static final String RYAN_AIR_ONE_WAY_URL_SAMPLE = String.join("",
            "https://www.ryanair.com/api/farfnd/v4/oneWayFares",
            "?departureAirportIataCode=WRO",
            "&outboundDepartureDateFrom=2026-03-01",
            "&market=en-gb",
            "&adultPaxCount=1",
            "&outboundDepartureDateTo=2026-03-31",
            "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY, SUNDAY",
            "&arrivalAirportCategoryCodes=SEA",
            "&outboundDepartureTimeFrom=07:00",
            "&outboundDepartureTimeTo=13:00",
            "&priceValueTo=300",
            "&currency=PLN");


    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd HH:mm");

    public static IataCode AIRPORT_FROM = IataCode.WRO;
    public static SearchMode SEARCH_MODE = SearchMode.ONE_WAY;

}
