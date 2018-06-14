package com.ibm.esun.esunmobilebank;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ibm.esun.esunmobilebank.model.HttpTask;
import com.ibm.esun.esunmobilebank.model.api.UrlFactory;

public class GoldPassbookActivity extends Activity implements HttpTask.ICallback{

    public final String TAG = GoldPassbookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doGetGoldPassbookInfo();
    }

    public void doGetGoldPassbookInfo() {
        HttpTask task = new HttpTask();
        task.setCallback(this);
        task.execute(UrlFactory.getUrl(UrlFactory.Target.GetGoldRate));
    }

    @Override
    public void onHttpResult(int statusCode, String jsonData) {
        Log.e(TAG, "jsonData = " + jsonData);
    }
}
