package com.seleon.flightscanner.kiwi.dto.response.calendar;

import lombok.Data;

@Data
public class RatedPrice {
    private Price price;
    private String rating;
}
