package com.ibm.esun.esunmobilebank;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.esun.esunmobilebank.common.Util;
import com.ibm.esun.esunmobilebank.model.GoldBean;
import com.ibm.esun.esunmobilebank.model.HttpTask;
import com.ibm.esun.esunmobilebank.model.api.UrlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoldPassbookActivity extends Activity implements HttpTask.ICallback {

    public final String TAG = GoldPassbookActivity.class.getSimpleName();

    private LinearLayout mListView;

    private TextView mKilogram, mGrams500, mGrams250, mGrams100, mQuoteDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldpassbook);

        findView();
        doGetGoldPassbookInfo();
    }

    private void findView() {
        mListView = findViewById(R.id.data_list);
        mKilogram = findViewById(R.id.kilogram);
        mGrams500 = findViewById(R.id.grams500);
        mGrams250 = findViewById(R.id.grams250);
        mGrams100 = findViewById(R.id.grams100);
        mQuoteDate = findViewById(R.id.quote_date);
    }

    public void doGetGoldPassbookInfo() {
        HttpTask task = new HttpTask();
        task.setCallback(this);
        task.execute(UrlFactory.getUrl(UrlFactory.Target.GetGoldRate));
    }


    private String mJsonData;

    protected String getJsonData() {
        return mJsonData;
    }

    @Override
    public void onHttpResult(int statusCode, String jsonData) {
        if(HttpTask.STATUS_OK == statusCode) {
            mJsonData = jsonData;
            List<GoldBean> data = getPriceList(jsonData);
            createPriceItem(data);
            assignDPDiff(data);
        } else {
            Util.showNetworkErrorAlert(this);
        }
    }

    private void createPriceItem(final List<GoldBean> aPriceList) {
        final LinearLayout listView = findViewById(R.id.data_list);
        final View twdItem = getView(aPriceList.get(4));
        final View usItem = getView(aPriceList.get(5));

        listView.addView(twdItem);
        listView.addView(usItem);
    }

    private String getSpec(final GoldBean data) {
        String specStr = "";
        if(data.getGoodName().equals("黃金存摺1盎司")) {
            specStr = "盎司";
        } else if (data.getGoodName().equals("黃金存摺1公克")) {
            specStr = "克";
        }
        return specStr;
    }

    private void assignDPDiff(final List<GoldBean> aPriceList) {
        mKilogram.setText(aPriceList.get(0).getDPDiff());
        mGrams500.setText(aPriceList.get(1).getDPDiff());
        mGrams250.setText(aPriceList.get(2).getDPDiff());
        mGrams100.setText(aPriceList.get(3).getDPDiff());
        mQuoteDate.setText("報價時間 " + aPriceList.get(3).getInfoDateTime());
    }

    private View getView(final GoldBean data) {
        final String countryNameStr = data.getCurrencyText();
        final String specStr = data.getGoodName();
        final String buyStr = data.getBuy();
        final String sellStr = data.getSell();
        final String countryId = data.getCurrency();

        View item = LayoutInflater.from(getApplicationContext()).inflate(R.layout.gold_price_item, null);

        ImageView countryIcon = item.findViewById(R.id.country_icon);
        countryIcon.setImageResource(getIconByCountry(countryId));

        TextView countryName = item.findViewById(R.id.country_name);
        countryName.setText(countryNameStr);

        TextView spec = item.findViewById(R.id.spec);
        spec.setText(getSpec(data));

        TextView buy = item.findViewById(R.id.buy);
        buy.setText(buyStr);

        TextView sell = item.findViewById(R.id.sell);
        sell.setText(sellStr);

        TextView quoteDate = item.findViewById(R.id.quote_date);
        quoteDate.setText("報價時間 " + data.getInfoDateTime());
        return item;
    }


    private int getIconByCountry(final String countryId) {
        int icon = R.drawable.flag_taiwan; // default.
        switch (countryId) {
            case "NTD":
                icon = R.drawable.flag_taiwan;
                break;

            case "USD":
                icon = R.drawable.flag_usa;
                break;
        }
        return icon;
    }


    protected List<GoldBean> getPriceList(final String jsonStr){
        final List<GoldBean> goldList = new ArrayList();
        try {

            JSONArray jsonArr = new JSONObject(jsonStr).getJSONArray("GoldPrice");
            JSONObject jsonObj = null;

            for(int i = 0; i <  jsonArr.length(); i++) {
                jsonObj = (JSONObject) jsonArr.get(i);
                GoldBean bean =  new GoldBean();
                bean.setBuy((String)jsonObj.get("Buy"));
                bean.setCurcd((String)jsonObj.get("Curcd"));
                bean.setCurrency((String)jsonObj.get("Currency"));
                bean.setCurrencyText((String)jsonObj.get("CurrencyText"));
                bean.setDPDiff((String)jsonObj.get("DPDiff"));
                bean.setGoodName((String)jsonObj.get("GoodName"));
                bean.setGoodNo((String)jsonObj.get("GoodNo"));
                bean.setInfoDateTime((String)jsonObj.get("InfoDateTime"));
                bean.setSell((String)jsonObj.get("Sell"));
                bean.setSellT((String)jsonObj.get("SellT"));
                goldList.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goldList;
    }


}
