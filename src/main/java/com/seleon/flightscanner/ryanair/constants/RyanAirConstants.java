package com.seleon.flightscanner.ryanair.constants;

import java.time.format.DateTimeFormatter;

public class RyanAirConstants {

    public static final String RYAN_AIR_ONE_WAY_URL_SAMPLE = String.join("",
            "https://www.ryanair.com/api/farfnd/v4/oneWayFares",
            "?arrivalAirportCategoryCodes=SEA",
            "&market=en-gb",
            "&adultPaxCount=1",
            "&departureAirportIataCode=WRO",

            "&outboundDepartureDateFrom=2026-03-01",
            "&outboundDepartureDateTo=2026-03-31",
            "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY, SUNDAY",
            "&outboundDepartureTimeFrom=07:00",
            "&outboundDepartureTimeTo=13:00",

            "&priceValueTo=300",
            "&currency=PLN");


    public static final String RYAN_AIR_RETURN_TRIP_URL_SAMPLE =
            "https://www.ryanair.com/api/farfnd/v4/roundTripFares" +
            "?arrivalAirportCategoryCodes=SEA" +
            "&market=en-hr&adultPaxCount=1" +
            "&departureAirportIataCode=WRO" +

            "&outboundDepartureDateFrom=2026-03-01" +
            "&outboundDepartureDateTo=2026-03-30" +
            "&outboundDepartureDaysOfWeek=THURSDAY,FRIDAY,SATURDAY" +
            "&outboundDepartureTimeFrom=05:00" +
            "&outboundDepartureTimeTo=13:00" +

            "&durationFrom=1" +
            "&durationTo=3" +

            "&inboundDepartureDateFrom=2026-03-02" +
            "&inboundDepartureDateTo=2026-03-31" +
            "&inboundDepartureTimeFrom=16:00" +
            "&inboundDepartureTimeTo=23:59";


    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd HH:mm");

}
