package com.fab.challengeaa001.ui.currencyexchange.rateslist;

public class CurrencyRate {

    private String symbol;
    private String rate;

    public CurrencyRate(String symbol, String rate) {
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
