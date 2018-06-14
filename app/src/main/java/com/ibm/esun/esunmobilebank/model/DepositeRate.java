package com.ibm.esun.esunmobilebank.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DepositeRate {
    @JsonProperty("title")
    private String title;

    @JsonProperty("fRate")
    private String fRate;

    @JsonProperty("mRate")
    private String mRate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getfRate() {
        return fRate;
    }

    public void setfRate(String fRate) {
        this.fRate = fRate;
    }

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }
}
