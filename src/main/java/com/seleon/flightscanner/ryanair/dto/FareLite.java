package com.seleon.flightscanner.ryanair.dto;

import com.seleon.flightscanner.ryanair.constants.RyanAirConstants;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class FareLite {
    private String outboundDepartureIataCode; // airport from my home
    private String outboundArrivalIataCode; // airport to destination
    private String outboundArrivalCity;
    private LocalDateTime outboundDepartureDate;
    private double outboundPriceValue;
    private String outboundCurrencyCode;

    private String inboundDepartureIataCode; // airport from destination
    private String inboundArrivalIataCode; // airport to my home
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
        String route = outboundDepartureIataCode + " - " + outboundArrivalIataCode;
        String from = formatDate(outboundDepartureDate);
        String to = inboundDepartureIataCode != null ? formatDate(inboundDepartureDate) : "";

        String price = outboundCurrencyCode + " " + formatPrice(outboundPriceValue);
        if (inboundDepartureIataCode != null) {
            double totalPrice = outboundPriceValue + inboundPriceValue;
            price = price + " + " + formatPrice(inboundPriceValue) + " = " + formatPrice(totalPrice);
        }

        String result = String.format("{ '%s', from='%s', to='%s', %s, %s }", route, from, to, price, outboundArrivalCity);
        return result;
    }

    private String formatDate(LocalDateTime dateTime) {
        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return dateTime.format(RyanAirConstants.TIME_FORMATTER).toUpperCase() + " " + dayOfWeek;
    }

    private String formatPrice(double price) {
        return String.format("%.2f", price);
    }
}
