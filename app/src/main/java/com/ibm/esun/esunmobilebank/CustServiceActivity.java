package com.ibm.esun.esunmobilebank;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ibm.esun.esunmobilebank.model.HttpTask;
import com.ibm.esun.esunmobilebank.model.api.UrlFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustServiceActivity extends AppCompatActivity implements HttpTask.ICallback {

    private ArrayList<Map<String, String>> creditCardTel;
    private ArrayList<Map<String, String>> depositTel;
    private ArrayList<Map<String, String>> investmentTel;

    private Toolbar toolbar;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Button investment_btn1;
    private Button investment_btn2;

    private Button deposit_btn1;
    private Button deposit_btn2;

    private Button fixed_btn1;
    private Button fixed_btn2;

    public final String TAG = CustServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_service);
        getBusinessPhone();
        initToolbar();
        initFixedButton();
    }
    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void initFixedButton() {
        fixed_btn1 = findViewById(R.id.loan_bth);
        fixed_btn2 = findViewById(R.id.all_service_btn);
        fixed_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog("信貸申請", "0221821313");
            }
        });
        fixed_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog("24小時客服專線", "0221821313");
            }
        });
    }
    private void initCreditCardNumber() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        button1.setText(creditCardTel.get(0).get("title"));
        button2.setText(creditCardTel.get(1).get("title"));
        button3.setText(creditCardTel.get(2).get("title"));
        button4.setText(creditCardTel.get(3).get("title"));
        button5.setText(creditCardTel.get(4).get("title"));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(creditCardTel.get(0).get("title"), creditCardTel.get(0).get("phone"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(creditCardTel.get(1).get("title"), creditCardTel.get(1).get("phone"));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(creditCardTel.get(2).get("title"), creditCardTel.get(2).get("phone"));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(creditCardTel.get(3).get("title"), creditCardTel.get(3).get("phone"));
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(creditCardTel.get(4).get("title"), creditCardTel.get(4).get("phone"));
            }
        });
    }

    private void makePhoneCall(String phoneNum) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNum));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CustServiceActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }else {
            startActivity(callIntent);
            Log.d(TAG, phoneNum.toString());
        }
    }

    private void initInvestmentNumber(){
        investment_btn1 = findViewById(R.id.investment_btn1);
        investment_btn2 = findViewById(R.id.investment_btn2);
        investment_btn1.setText(investmentTel.get(0).get("title"));
        investment_btn2.setText(investmentTel.get(1).get("title"));
        investment_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(investmentTel.get(0).get("title"), investmentTel.get(0).get("phone"));
            }
        });
        investment_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(investmentTel.get(1).get("title"), investmentTel.get(1).get("phone"));
            }
        });

    }
    private void initDepositNumber(){
        deposit_btn1 = findViewById(R.id.deposit_btn1);
        deposit_btn2 = findViewById(R.id.deposit_btn2);
        deposit_btn1.setText(depositTel.get(0).get("title"));
        deposit_btn2.setText(depositTel.get(1).get("title"));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(depositTel.get(0).get("title"), depositTel.get(0).get("phone"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(depositTel.get(1).get("title"), depositTel.get(1).get("phone"));
            }
        });
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
            JSONArray creditCardTel = obj.getJSONArray("CreditCardBusiness");
            JSONArray depositTel = obj.getJSONArray("DepositeInterestBusiness");
            JSONArray  investmentTel = obj.getJSONArray("InvestmentBusiness");
            this.creditCardTel = phoneJsonToArray(creditCardTel);
            this.depositTel = phoneJsonToArray(depositTel);
            this.investmentTel = phoneJsonToArray(investmentTel);

            Log.d(TAG, creditCardTel.toString());
            Log.d(TAG, depositTel.toString());
            Log.d(TAG, investmentTel.toString());

            initCreditCardNumber();
            initInvestmentNumber();
            initDepositNumber();
        } catch (Throwable t) {
            Log.e(TAG, "Could not parse malformed JSON: \"" + jsonData + "\"");
        }
    }

    private ArrayList<Map<String, String>> phoneJsonToArray(JSONArray phoneJson){
        ArrayList<Map<String, String>> result = new ArrayList<Map<String,String>>();
        for(int i = 0; i < phoneJson.length(); i++){
            try {
                HashMap temp = new HashMap();
                JSONObject obj = phoneJson.getJSONObject(i);
                temp.put("title", obj.getString("title"));
                temp.put("phone", obj.getString("phone"));
                result.add(temp);
            } catch (Throwable t) {
                Log.e(TAG, "Could not parse JSON: \"" + phoneJson + "\"");
            }
        }
        return result;
    }

    private void alertDialog(String text, String phoneNum){
        final String phoneNumber = phoneNum;
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("外撥提醒")
                .setMessage("確定播至" + text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        makePhoneCall(phoneNumber);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(CustServiceActivity.this, "未開啟權限", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}