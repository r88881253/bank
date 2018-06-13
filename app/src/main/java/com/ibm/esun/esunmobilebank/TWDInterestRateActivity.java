package com.ibm.esun.esunmobilebank;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.ibm.esun.esunmobilebank.Adapter.TWDInterestRateAdapter;

public class TWDInterestRateActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twd_deposit_interest_rate);


        ListView listview = (ListView) findViewById(R.id.list_view_deposit);
        //ListView 要顯示的內容
//        String[] str = {"新北市","台北市","台中市","台南市","高雄市","新北市","台北市","台中市","台南市","高雄市","新北市","台北市","台中市","台南市","高雄市"};
//        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
//        ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                str);


        String[][] str2 = {{"支票存款", "0.010", "0.01"}, {"支票存款", "0.010", "0.01"}, {"支票存款", "0.010", "0.01"}};

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TWDInterestRateAdapter adapter = new TWDInterestRateAdapter(str2,inflater);


        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.note_below, null, false);
        listview.addFooterView(footerView);

        listview.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");

        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigation);




//
//        View footerView = ((LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.note_below, null, false);
//        ListView.addFooterView(footerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.twd_interst_rate, menu);
        //隱藏save的icon
        menu.findItem(R.id.menu_lending).setVisible(true);
        menu.findItem(R.id.menu_deposit).setVisible(false);
        return true;
    }
}
