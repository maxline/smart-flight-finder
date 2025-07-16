package com.seleon.flightscanner.kiwi.dto.response.calendar;

import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class CalendarEntryLite {
    private LocalDateTime date;
    private String rating;
    private BigDecimal price;

    public CalendarEntryLite(CalendarEntry entry) {
        this.date = entry.getDate();
        this.rating = entry.getRatedPrice().getRating();
        this.price = entry.getRatedPrice().getPrice().getAmount();
    }

    // {'AUG-01 Fri', 249.38 PLN, AVERAGE}
    public String toBrief() {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-dd");

        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String flyAt = date.format(dateFormatter).toUpperCase() + " " + dayOfWeek;
        DecimalFormat df = new DecimalFormat("#0.00");

        String brief = "{ '" + flyAt + '\'' + ", " + df.format(price) + " PLN }";
        return brief;
    }

}
