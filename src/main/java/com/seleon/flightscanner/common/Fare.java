package com.seleon.flightscanner.common;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Normalized fare returned by any FareSource.
 * inboundDate is null for one-way fares.
 */
public record Fare(
        String sourceName,
        String origin,
        String destination,
        LocalDateTime outboundDate,
        LocalDateTime inboundDate,
        BigDecimal price,
        String currency,
        String flightId
) {
}
