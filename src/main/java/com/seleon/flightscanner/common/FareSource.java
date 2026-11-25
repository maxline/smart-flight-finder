package com.seleon.flightscanner.common;

import java.util.List;

/**
 * A source of flight fares (RyanAir, Wizz Air, and later others).
 * Each implementation maps the common request to its own API and returns normalized fares.
 */
public interface FareSource {

    String name();

    List<Fare> search(FareSearchRequest request);
}
