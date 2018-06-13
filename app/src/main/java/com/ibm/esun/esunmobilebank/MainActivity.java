package com.ibm.esun.esunmobilebank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toGoldPassbookPage();
    }

    private void toGoldPassbookPage() {

        Button goldBtn = (Button) findViewById(R.id.button2);
        goldBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent goldPassbookPage = new Intent();
                goldPassbookPage.setClass(MainActivity.this, GoldPassbookActivity.class);
                startActivity(goldPassbookPage);
            }
        });
    }

}
