package com.fab.challengeaa001.data.remote;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class GetTimeFramedRatesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("timeseries")
    @Expose
    private Boolean timeseries;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("rates")
    @Expose
    private JsonObject rates;

    public JsonObject getRates() {
        return rates;
    }

    public void setRates(JsonObject rates) {
        this.rates = rates;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getTimeseries() {
        return timeseries;
    }

    public void setTimeseries(Boolean timeseries) {
        this.timeseries = timeseries;
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

}


