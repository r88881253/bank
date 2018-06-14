package com.ibm.esun.esunmobilebank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanRate {
    @JsonProperty("title")
    private String title;

    @JsonProperty("Rate")
    private String rate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
