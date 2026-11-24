package com.seleon.flightscanner.ryanair.run;

import com.seleon.flightscanner.common.Fare;
import com.seleon.flightscanner.common.FareSearchRequest;
import com.seleon.flightscanner.common.FareSource;
import com.seleon.flightscanner.common.TripType;
import com.seleon.flightscanner.ryanair.dto.FlightData;
import com.seleon.flightscanner.ryanair.dto.Inbound;
import com.seleon.flightscanner.ryanair.dto.Outbound;
import com.seleon.flightscanner.ryanair.dto.SearchParameters;
import com.seleon.flightscanner.ryanair.enums.ArrivalCategory;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.utils.JsonUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RyanAir adapter: maps the common FareSearchRequest to the RyanAir fares API
 * and normalizes the response into common Fare objects.
 */
public class RyanAirFareSource implements FareSource {

    private static final String NAME = "RyanAir";
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");

    // RyanAir-specific defaults, used when the common request does not set them
    private static final String DEFAULT_TIME_FROM = "00:00";
    private static final String DEFAULT_TIME_TO = "23:59";
    private static final String DEFAULT_INBOUND_TIME_FROM = "16:00";
    private static final String DEFAULT_INBOUND_TIME_TO = "23:59";
    private static final int DEFAULT_PRICE_VALUE_TO = 10000;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final int durationFrom;
    private final int durationTo;

    public RyanAirFareSource() {
        this(1, 4);
    }

    public RyanAirFareSource(int durationFrom, int durationTo) {
        this.durationFrom = durationFrom;
        this.durationTo = durationTo;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public List<Fare> search(FareSearchRequest request) {
        String url = RyanAirSearch.urlCreator(toSearchParameters(request));
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() / 100 != 2) {
                throw new IllegalStateException("RyanAir returned HTTP " + response.statusCode());
            }
            return toFares(JsonUtil.jsonToObject(response.body(), FlightData.class));
        } catch (URISyntaxException | IOException e) {
            throw new IllegalStateException("RyanAir fare search failed", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("RyanAir fare search interrupted", e);
        }
    }

    SearchParameters toSearchParameters(FareSearchRequest request) {
        if (request.getHolidayType() == null) {
            throw new IllegalArgumentException("RyanAir requires a holiday type");
        }

        SearchMode searchMode = request.getTripType() == TripType.ROUND_TRIP ? SearchMode.ROUND : SearchMode.ONE_WAY;

        // Inbound range: use the request if given, otherwise offset from the outbound range
        var inboundFrom = request.getInboundFrom() != null
                ? request.getInboundFrom()
                : request.getOutboundFrom().plusDays(durationFrom);
        var inboundTo = request.getInboundTo() != null
                ? request.getInboundTo()
                : request.getOutboundTo().plusDays(durationTo);

        return SearchParameters.builder()
                .dateFrom(request.getOutboundFrom().toString())
                .dateTo(request.getOutboundTo().toString())
                .airportFrom(request.getOrigin().name())
                .arrivalCategory(ArrivalCategory.valueOf(request.getHolidayType().name()))
                .searchMode(searchMode)
                .departureTimeFrom(request.getDepartureTimeFrom() != null ? request.getDepartureTimeFrom().format(TIME) : DEFAULT_TIME_FROM)
                .departureTimeTo(request.getDepartureTimeTo() != null ? request.getDepartureTimeTo().format(TIME) : DEFAULT_TIME_TO)
                .daysOfWeek(daysOfWeek(request.getDaysOfWeek()))
                .durationFrom(durationFrom)
                .durationTo(durationTo)
                .inboundDepartureDateFrom(inboundFrom.toString())
                .inboundDepartureDateTo(inboundTo.toString())
                .inboundDepartureTimeFrom(DEFAULT_INBOUND_TIME_FROM)
                .inboundDepartureTimeTo(DEFAULT_INBOUND_TIME_TO)
                .priceValueTo(request.getMaxPrice() != null ? request.getMaxPrice().intValue() : DEFAULT_PRICE_VALUE_TO)
                .currency(request.getCurrency())
                .build();
    }

    static List<Fare> toFares(FlightData flightData) {
        List<Fare> fares = new ArrayList<>();
        if (flightData == null || flightData.getFares() == null) {
            return fares;
        }
        for (com.seleon.flightscanner.ryanair.dto.Fare raw : flightData.getFares()) {
            Outbound outbound = raw.getOutbound();
            Inbound inbound = raw.getInbound();
            if (outbound == null) {
                continue;
            }
            double total = outbound.getPrice().getValue() + (inbound != null ? inbound.getPrice().getValue() : 0);
            fares.add(new Fare(
                    NAME,
                    outbound.getDepartureAirport().getIataCode(),
                    outbound.getArrivalAirport().getIataCode(),
                    outbound.getDepartureDate(),
                    inbound != null ? inbound.getDepartureDate() : null,
                    BigDecimal.valueOf(total),
                    outbound.getPrice().getCurrencyCode(),
                    outbound.getFlightKey()
            ));
        }
        return fares;
    }

    private static String daysOfWeek(Set<DayOfWeek> days) {
        if (days == null || days.isEmpty()) {
            return Arrays.stream(DayOfWeek.values()).map(Enum::name).collect(Collectors.joining(","));
        }
        return days.stream()
                .sorted()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
