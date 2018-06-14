package com.ibm.esun.esunmobilebank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class DepositInterstRate {
    @JsonProperty("UpdateTime")
    private String updateTime;

    @JsonProperty("depositeInterestRateList")
    private Map<String, List<DepositeRate>> depositeInterestRateList;

    @JsonProperty("loanInterestRateList")
    private Map<String,List<LoanRate>> loanInterestRateList;


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, List<DepositeRate>> getDepositeInterestRateList() {
        return depositeInterestRateList;
    }

    public void setDepositeInterestRateList(Map<String, List<DepositeRate>> depositeInterestRateList) {
        this.depositeInterestRateList = depositeInterestRateList;
    }

    public Map<String, List<LoanRate>> getLoanInterestRateList() {
        return loanInterestRateList;
    }

    public void setLoanInterestRateList(Map<String, List<LoanRate>> loanInterestRateList) {
        this.loanInterestRateList = loanInterestRateList;
    }
}
