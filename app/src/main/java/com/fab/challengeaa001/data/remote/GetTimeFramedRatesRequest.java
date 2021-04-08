package com.fab.challengeaa001.data.remote;

public class GetTimeFramedRatesRequest {

    private String startDate;
    private String endDate;
    private String base;
    private String symbol;

    public GetTimeFramedRatesRequest(String startDate, String endDate, String base, String symbol) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.base = base;
        this.symbol = symbol;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
