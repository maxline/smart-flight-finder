package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.ryanair.dto.SearchParameters;
import com.seleon.flightscanner.ryanair.enums.ArrivalCategory;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Characterization tests: lock in the current URL built by RyanAirSearch.urlCreator.
class RyanAirSearchUrlTest {

    private SearchParameters.SearchParametersBuilder baseParameters(SearchMode mode) {
        return SearchParameters.builder()
                .dateFrom("2026-12-01")
                .dateTo("2026-12-31")
                .airportFrom("WRO")
                .arrivalCategory(ArrivalCategory.BEACH)
                .searchMode(mode)
                .departureTimeFrom("05:45")
                .departureTimeTo("13:00")
                .daysOfWeek("FRIDAY,SATURDAY,SUNDAY")
                .durationFrom(1)
                .durationTo(4)
                .inboundDepartureDateFrom("2026-12-02")
                .inboundDepartureDateTo("2026-12-05")
                .inboundDepartureTimeFrom("16:00")
                .inboundDepartureTimeTo("23:59")
                .priceValueTo(300)
                .currency("PLN");
    }

    @Test
    void oneWayUrlContainsAllParametersInCurrentOrder() {
        String url = RyanAirSearch.urlCreator(baseParameters(SearchMode.ONE_WAY).build());

        assertEquals(
                "https://www.ryanair.com/api/farfnd/v4/oneWayFares"
                        + "?departureAirportIataCode=WRO"
                        + "&arrivalAirportCategoryCodes=SEA"
                        + "&outboundDepartureDateFrom=2026-12-01"
                        + "&outboundDepartureDateTo=2026-12-31"
                        + "&outboundDepartureDaysOfWeek=FRIDAY,SATURDAY,SUNDAY"
                        + "&outboundDepartureTimeFrom=05:45"
                        + "&outboundDepartureTimeTo=13:00"
                        + "&durationFrom=1&durationTo=4"
                        + "&inboundDepartureDateFrom=2026-12-02"
                        + "&inboundDepartureDateTo=2026-12-05"
                        + "&inboundDepartureTimeFrom=16:00"
                        + "&inboundDepartureTimeTo=23:59"
                        + "&priceValueTo=300"
                        + "&currency=PLN"
                        + "&adultPaxCount=1"
                        + "&market=en-gb",
                url);
    }

    @Test
    void roundTripUsesRoundTripEndpoint() {
        String url = RyanAirSearch.urlCreator(baseParameters(SearchMode.ROUND).build());

        assertEquals(
                "https://www.ryanair.com/api/farfnd/v4/roundTripFares",
                url.substring(0, url.indexOf('?')));
    }
}
