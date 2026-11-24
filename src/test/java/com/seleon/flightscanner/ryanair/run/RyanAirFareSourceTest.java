package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.common.Fare;
import com.seleon.flightscanner.common.FareSearchRequest;
import com.seleon.flightscanner.common.HolidayType;
import com.seleon.flightscanner.common.TripType;
import com.seleon.flightscanner.nearbyairports.IataCode;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RyanAirFareSourceTest {

    private final RyanAirFareSource source = new RyanAirFareSource(1, 4);

    private FareSearchRequest.FareSearchRequestBuilder oneWayRequest() {
        return FareSearchRequest.builder()
                .origin(IataCode.WRO)
                .outboundFrom(LocalDate.parse("2026-12-01"))
                .outboundTo(LocalDate.parse("2026-12-31"))
                .tripType(TripType.ONE_WAY)
                .holidayType(HolidayType.BEACH)
                .daysOfWeek(Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
                .departureTimeFrom(LocalTime.of(5, 45))
                .departureTimeTo(LocalTime.of(13, 0))
                .currency("PLN")
                .maxPrice(new BigDecimal("300"));
    }

    @Test
    void oneWayRequestMapsToSameUrlAsBeforeTheRefactor() {
        var parameters = source.toSearchParameters(oneWayRequest().build());

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
                        + "&inboundDepartureDateTo=2027-01-04"
                        + "&inboundDepartureTimeFrom=16:00"
                        + "&inboundDepartureTimeTo=23:59"
                        + "&priceValueTo=300"
                        + "&currency=PLN"
                        + "&adultPaxCount=1"
                        + "&market=en-gb",
                RyanAirSearch.urlCreator(parameters));
    }

    @Test
    void roundTripUsesRoundTripEndpointAndExplicitInboundRange() {
        var request = oneWayRequest()
                .tripType(TripType.ROUND_TRIP)
                .inboundFrom(LocalDate.parse("2026-12-03"))
                .inboundTo(LocalDate.parse("2026-12-06"))
                .build();

        var parameters = source.toSearchParameters(request);

        assertEquals("roundTripFares", parameters.getSearchMode().getValue());
        assertEquals("2026-12-03", parameters.getInboundDepartureDateFrom());
        assertEquals("2026-12-06", parameters.getInboundDepartureDateTo());
    }

    @Test
    void missingHolidayTypeIsRejected() {
        var request = oneWayRequest().holidayType(null).build();

        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> source.toSearchParameters(request));
    }

    @Test
    void normalizesRyanAirResponseIntoCommonFares() {
        String json = FilesUtil.load("src/test/resources/", "ryan_air_KRK_ONE_WAY_001.json");
        FlightData flightData = JsonUtil.jsonToObject(json, FlightData.class);
        int rawCount = flightData.getFares().size();

        List<Fare> fares = RyanAirFareSource.toFares(flightData);

        assertEquals(rawCount, fares.size());
        for (Fare fare : fares) {
            assertEquals("RyanAir", fare.sourceName());
            assertNotNull(fare.origin());
            assertNotNull(fare.destination());
            assertNotNull(fare.outboundDate());
            assertNotNull(fare.price());
            assertFalse(fare.price().signum() < 0);
        }
    }
}
