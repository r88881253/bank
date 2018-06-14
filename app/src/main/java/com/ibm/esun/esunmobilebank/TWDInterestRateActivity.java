package com.ibm.esun.esunmobilebank;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.esun.esunmobilebank.Adapter.DepositAdapter;
import com.ibm.esun.esunmobilebank.Adapter.LendingAdapter;
import com.ibm.esun.esunmobilebank.common.Util;
import com.ibm.esun.esunmobilebank.model.DepositInterstRate;
import com.ibm.esun.esunmobilebank.model.DepositeRate;
import com.ibm.esun.esunmobilebank.model.HttpTask;
import com.ibm.esun.esunmobilebank.model.LoanRate;
import com.ibm.esun.esunmobilebank.model.api.UrlFactory;

import java.util.List;

public class TWDInterestRateActivity extends AppCompatActivity implements HttpTask.ICallback{

    private Toolbar toolbar;

    private String deposit = "存款";
    private String lending = "放款";

    private String depositTitle = "臺幣存款利率";
    private String lendingTitle = "臺幣放款利率";


    private String updateTime;

    private LinearLayout depositTitleBar;
    private LinearLayout lendingTitleBar;

    private ListView depositListview;
    private ListView lendingListview;


    public final String TAG = TWDInterestRateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twd_deposit_interest_rate);

        depositListview = (ListView) findViewById(R.id.list_view_deposit);
        lendingListview = (ListView) findViewById(R.id.list_view_lending);

        depositTitleBar = (LinearLayout) findViewById(R.id.deposit_title_bar);
        lendingTitleBar = (LinearLayout) findViewById(R.id.lending_title_bar);

        initToolbar();

        doGetDepositRateInfo();

        lendingListview.setVisibility(View.INVISIBLE);
        lendingTitleBar.setVisibility(View.INVISIBLE);
        depositListview.setVisibility(View.VISIBLE);
        depositTitleBar.setVisibility(View.VISIBLE);

    }


    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView toolBarMenu = (TextView) findViewById(R.id.toolbar_title);
        toolBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deposit.equals(toolBarMenu.getText().toString())){
                    toolBarMenu.setText(lending);
                    toolbar.setTitle(depositTitle);
                    lendingListview.setVisibility(View.INVISIBLE);
                    lendingTitleBar.setVisibility(View.INVISIBLE);
                    depositListview.setVisibility(View.VISIBLE);
                    depositTitleBar.setVisibility(View.VISIBLE);
                }
                else{
                    toolBarMenu.setText(deposit);
                    toolbar.setTitle(lendingTitle);
                    lendingListview.setVisibility(View.VISIBLE);
                    lendingTitleBar.setVisibility(View.VISIBLE);
                    depositListview.setVisibility(View.INVISIBLE);
                    depositTitleBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_navigation);
    }



    public void doGetDepositRateInfo() {

        System.out.println(Util.isNetworkAvailable(this));

        if(Util.isNetworkAvailable(this)){
            HttpTask task = new HttpTask();
            task.setCallback(this);
            task.execute(UrlFactory.getUrl(UrlFactory.Target.GetRate));
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("系統訊息")
                    .setMessage("請開啟網路功能");
            // Add the buttons
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            builder.create().show();
        }
    }

    @Override
    public void onHttpResult(int statusCode, String jsonData) {

        if(statusCode != 200){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Add the buttons
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            Log.d(TAG, "status code is not 200");
        }else{
            //parse jsonString to object
            try {
                ObjectMapper mapper = new ObjectMapper();

                DepositInterstRate objDepositInterstRate = mapper.readValue(jsonData, DepositInterstRate.class);

                updateTime = objDepositInterstRate.getUpdateTime();

                initDepositListView(objDepositInterstRate.getDepositeInterestRateList().get("DepositeRate"), updateTime);
                initLendingListView(objDepositInterstRate.getLoanInterestRateList().get("LoanRate"), updateTime);

                Log.d(TAG, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objDepositInterstRate));
            } catch (Throwable t) {
                Log.e(TAG, "Could not parse malformed JSON: \"" + jsonData + "\"");
            }

        }

    }

    public void initDepositListView(List<DepositeRate> itemList , String updateTime){

        String depositNotes = "＊單位：年息\n" +
                "＊生效日期："+updateTime+ "\n" +
                "＊其他月份存期及畸零天期、定期性存款按已掛牌前一較低期別之利率計息。\n" +
                "＊本資料僅供參考，實際資料以各營業單位為準。\n"+
                "＊新臺幣參佰萬元(含)以上者，適用大額存款。";



        LayoutInflater depositInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DepositAdapter depositAdapter = new DepositAdapter(itemList, depositInflater);

        View depositFooterView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.note_below, null, false);
        TextView tvDepositNotes = (TextView) depositFooterView.findViewById(R.id.interestNotes);
        tvDepositNotes.setText(depositNotes);

        depositListview.addFooterView(depositFooterView);
        depositListview.setAdapter(depositAdapter);

    }

    public void initLendingListView(List<LoanRate> itemList, String updateTime){

        String lendingNotes = "＊單位：年息\n" +
                "＊生效日期："+ updateTime + "\n" +
                "＊本資料僅供參考，實際資料以各營業單位為準。\n" +
                "＊信用卡放款利率為「機動利率」。";


        LayoutInflater lendingInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LendingAdapter lendingAdapter = new LendingAdapter(itemList, lendingInflater);

        View lendingFooterView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.note_below, null, false);
        TextView tvLendingNotes = (TextView) lendingFooterView.findViewById(R.id.interestNotes);
        tvLendingNotes.setText(lendingNotes);

        lendingListview.addFooterView(lendingFooterView);
        lendingListview.setAdapter(lendingAdapter);

    }
//
//    //確認wifi有沒有連線
//    protected boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }

}
