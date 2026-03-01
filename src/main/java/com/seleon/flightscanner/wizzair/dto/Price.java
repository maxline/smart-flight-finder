package com.seleon.flightscanner.wizzair.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private BigDecimal amount;
    private String currencyCode;
    private BigDecimal exchangedAmount;
    private String exchangedCurrencyCode;

    public Price() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(BigDecimal exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public String getExchangedCurrencyCode() {
        return exchangedCurrencyCode;
    }

    public void setExchangedCurrencyCode(String exchangedCurrencyCode) {
        this.exchangedCurrencyCode = exchangedCurrencyCode;
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currencyCode='" + currencyCode + '\'' +
                ", exchangedAmount=" + exchangedAmount +
                ", exchangedCurrencyCode='" + exchangedCurrencyCode + '\'' +
                '}';
    }
}
