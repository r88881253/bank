package com.ibm.esun.esunmobilebank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button customerServiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toGoldPassbookPage();
        customerServiceButton = (Button) findViewById(R.id.button3);
        customerServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                Intent i = new Intent(MainActivity.this, ChatbotActivity.class);
                startActivity(i);
            }
        });
    }

    private void toGoldPassbookPage() {

        Button goldBtn = (Button) findViewById(R.id.button2);
        goldBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent goldPassbookPage = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "TEST_STR");
                goldPassbookPage.putExtras(bundle);
                goldPassbookPage.setClass(MainActivity.this, GoldPassbookActivity.class);
                startActivity(goldPassbookPage);

            }
        });
    }

    public void onclickTWDInterestRate(View view) {

        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, TWDInterestRateActivity.class);
        startActivity(intent);

    }
}
