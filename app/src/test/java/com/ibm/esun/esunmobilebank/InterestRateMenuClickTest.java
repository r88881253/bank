package com.ibm.esun.esunmobilebank;


import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class InterestRateMenuClickTest {



    private Activity activity = Robolectric.buildActivity(TWDInterestRateActivity.class).create().get();

    private String deposit = "存款";
    private String lending = "放款";

    private String depositTitle = "臺幣存款利率";
    private String lendingTitle = "臺幣放款利率";

    TextView toolBarMenu = (TextView) activity.findViewById(R.id.toolbar_title);
    ListView depositListview = (ListView) activity.findViewById(R.id.list_view_deposit);
    ListView lendingListview = (ListView) activity.findViewById(R.id.list_view_lending);

    LinearLayout depositTitleBar = (LinearLayout) activity.findViewById(R.id.deposit_title_bar);
    LinearLayout lendingTitleBar = (LinearLayout) activity.findViewById(R.id.lending_title_bar);
    Toolbar toolbar = activity.findViewById(R.id.toolbar);


    @Test
    public void menuClickTwice() {

        toolBarMenu.performClick();
        menuClickAssert();
        toolBarMenu.performClick();
        menuClickAssert();

    }

    @Test
    public void menuClickAssert() {
        if(lending.equals(toolBarMenu.getText().toString())){
            assertEquals(toolbar.getTitle().toString(), depositTitle);
            assertEquals(View.VISIBLE, depositListview.getVisibility() );
            assertEquals(View.VISIBLE, depositTitleBar.getVisibility());
            assertEquals(View.INVISIBLE, lendingListview.getVisibility());
            assertEquals(View.INVISIBLE, lendingTitleBar.getVisibility());
        }else if(deposit.equals(toolBarMenu.getText().toString())){
            assertEquals(lendingTitle, toolbar.getTitle().toString());
            assertEquals(View.INVISIBLE, depositListview.getVisibility());
            assertEquals(View.INVISIBLE, depositTitleBar.getVisibility());
            assertEquals(View.VISIBLE, lendingListview.getVisibility());
            assertEquals(View.VISIBLE, lendingTitleBar.getVisibility());
        }

    }

}


