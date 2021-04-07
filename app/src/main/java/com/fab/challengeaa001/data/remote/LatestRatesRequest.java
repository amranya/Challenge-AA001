package com.fab.challengeaa001.data.remote;

public class LatestRatesRequest {

    private String symbol;

    public LatestRatesRequest(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
