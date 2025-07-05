package com.seleon.flightscanner.ryanair.dto;

import com.seleon.flightscanner.ryanair.constants.RyanAirConstants;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class FareLite {
    private String outboundDepartureIataCode;
    private String outboundArrivalIataCode;
    private String outboundArrivalCity;
    private LocalDateTime outboundDepartureDate;
    private double outboundPriceValue;
    private String outboundCurrencyCode;

    private String inboundDepartureIataCode;
    private String inboundArrivalIataCode;
    private LocalDateTime inboundDepartureDate;
    private double inboundPriceValue;
    private String inboundCurrencyCode;

    public FareLite(Outbound outbound, Inbound inbound) {
        this.outboundDepartureIataCode = outbound.getDepartureAirport().getIataCode();
        this.outboundArrivalIataCode = outbound.getArrivalAirport().getIataCode();
        this.outboundArrivalCity = outbound.getArrivalAirport().getCity().getName();
        this.outboundDepartureDate = outbound.getDepartureDate();
        this.outboundPriceValue = outbound.getPrice().getValue();
        this.outboundCurrencyCode = outbound.getPrice().getCurrencyCode();

        if (inbound != null) {
            this.inboundDepartureIataCode = inbound.getDepartureAirport().getIataCode();
            this.inboundArrivalIataCode = inbound.getArrivalAirport().getIataCode();
            this.inboundDepartureDate = inbound.getDepartureDate();
            this.inboundPriceValue = inbound.getPrice().getValue();
            this.inboundCurrencyCode = inbound.getPrice().getCurrencyCode();
        }
    }

    /**
     * @return {'KRK - CTA', flyAt='OCT-30 10:55 Thu', 119.0 PLN, Catania}
     */
    public String toBrief() {
        String dayOfWeek = outboundDepartureDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String outboundFlyAt = outboundDepartureDate.format(RyanAirConstants.TIME_FORMATTER).toUpperCase() + " " + dayOfWeek;

        String brief = "{" +
                '\'' + outboundDepartureIataCode +
                " - " + outboundArrivalIataCode + '\'' +
                ", flyAt='" + outboundFlyAt + '\'';

        if (inboundDepartureIataCode != null) {
            dayOfWeek = inboundDepartureDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String inboundFlyAt = inboundDepartureDate.format(RyanAirConstants.TIME_FORMATTER).toUpperCase() + " " + dayOfWeek;

            //brief = brief + ", \'" + inboundDepartureIataCode + " - " + inboundArrivalIataCode + '\'';
            brief = brief + ", flyAt='" + inboundFlyAt + '\'';
        }

        brief = brief + ", " + outboundPriceValue + " " + outboundCurrencyCode;

        if (inboundDepartureIataCode != null) {
            brief = brief + ", " + inboundPriceValue + " " + inboundCurrencyCode;
        }

        brief = brief + ", " + outboundArrivalCity + '}';
        return brief;
    }
}
