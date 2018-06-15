package com.ibm.esun.esunmobilebank.model;


import java.io.Serializable;

public class GoldBean implements Serializable {

    private static final long serialVersionUID = 7457930140883585250L;

    private String mBuy;

    private String mCurcd;

    private String mCurrency;

    private String mCurrencyText;

    private String mDPDiff;

    private String mGoodName;

    private String mGoodNo;

    private String mInfoDateTime;

    private String mSell;

    private String mSellT;

    public String getBuy() {
        return mBuy;
    }

    public String getCurcd() {
        return mCurcd;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public String getCurrencyText() {
        return mCurrencyText;
    }

    public String getDPDiff() {
        return mDPDiff;
    }

    public String getGoodName() {
        return mGoodName;
    }

    public String getGoodNo() {
        return mGoodNo;
    }

    public String getInfoDateTime() {
        return mInfoDateTime;
    }

    public String getSell() {
        return mSell;
    }

    public String getSellT() {
        return mSellT;
    }

    public void setBuy(String buy) {
        mBuy = buy;
    }

    public void setCurcd(String curcd) {
        mCurcd = curcd;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public void setCurrencyText(String currencyText) {
        mCurrencyText = currencyText;
    }

    public void setDPDiff(String dpDiff) {
        mDPDiff = dpDiff;
    }

    public void setGoodName(String goodName) {
        mGoodName = goodName;
    }

    public void setGoodNo(String goodNo) {
        mGoodNo = goodNo;
    }

    public void setInfoDateTime(String infoDateTime) {
        mInfoDateTime = infoDateTime;
    }

    public void setSell(String sell) {
        mSell = sell;
    }

    public void setSellT(String sellT) {
        mSellT = sellT;
    }
}