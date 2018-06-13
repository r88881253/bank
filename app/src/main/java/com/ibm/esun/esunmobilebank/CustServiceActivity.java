package com.ibm.esun.esunmobilebank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.ibm.esun.esunmobilebank.model.HttpTask;
import com.ibm.esun.esunmobilebank.model.api.UrlFactory;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustServiceActivity extends AppCompatActivity implements HttpTask.ICallback{

    private JSONArray creditCardTel;
    private JSONArray depositTel;
    private JSONArray investmentTel;

    public final String TAG = CustServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_service);
        getBusinessPhone();
    }

    private void initCreditCardNumber(){
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        button1.setText(creditCardTel);

    }

    private void getBusinessPhone() {
        HttpTask task = new HttpTask();
        task.setCallback(this);
        task.execute(UrlFactory.getUrl(UrlFactory.Target.GetBusinessPhone));
    }

    @Override
    public void onHttpResult(String jsonData) {
        //parse string to json
        try {
            JSONObject obj = new JSONObject(jsonData);
            creditCardTel = obj.getJSONArray("CreditCardBusiness");
            depositTel = obj.getJSONArray("DepositeInterestBusiness");
            investmentTel = obj.getJSONArray("InvestmentBusiness");
            Log.d(TAG, creditCardTel.toString());
            Log.d(TAG, depositTel.toString());
            Log.d(TAG, investmentTel.toString());
            initCreditCardNumber();
        } catch (Throwable t) {
            Log.e(TAG, "Could not parse malformed JSON: \"" + jsonData + "\"");
        }
    }
}