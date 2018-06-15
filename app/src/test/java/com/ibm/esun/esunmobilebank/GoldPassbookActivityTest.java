package com.ibm.esun.esunmobilebank;


import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.esun.esunmobilebank.model.GoldBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class GoldPassbookActivityTest {

    private GoldPassbookActivity mActivity;

    private List<GoldBean> mDataList;

    @Before
    public void onSetupView() {
        ShadowLog.stream = System.out;
        mActivity = Robolectric.setupActivity(GoldPassbookActivity.class);
    }

    @Test
    public void process() {
        checkJsonData();
        checkPriceList();
        checkDataOnView();
        checkDPDiff();
    }

    private void checkJsonData() {
        final String response = mActivity.getJsonData();
        assertNotNull(response);
    }

    private void checkPriceList() {
        mDataList = mActivity.getPriceList(mActivity.getJsonData());
        assertNotSame(0, mDataList.size());
    }

    private void checkDataOnView() {
        final LinearLayout table = mActivity.findViewById(R.id.data_list);
        final View item1 = table.getChildAt(0);
        final TextView buy1 = item1.findViewById(R.id.buy);
        final TextView sell1 = item1.findViewById(R.id.sell);

        assertEquals(buy1.getText().toString(), mDataList.get(4).getBuy());
        assertEquals(sell1.getText().toString(), mDataList.get(4).getSell());


        final View item2 = table.getChildAt(1);
        final TextView buy2 = item2.findViewById(R.id.buy);
        final TextView sell2 = item2.findViewById(R.id.sell);

        assertEquals(buy2.getText().toString(), mDataList.get(5).getBuy());
        assertEquals(sell2.getText().toString(), mDataList.get(5).getSell());
    }

    private void checkDPDiff() {
        final TextView kilogram = mActivity.findViewById(R.id.kilogram);
        final TextView grams500 = mActivity.findViewById(R.id.grams500);
        final TextView grams250 = mActivity.findViewById(R.id.grams250);
        final TextView grams100 = mActivity.findViewById(R.id.grams100);

        assertEquals(kilogram.getText().toString(), mDataList.get(0).getDPDiff());
        assertEquals(grams500.getText().toString(), mDataList.get(1).getDPDiff());
        assertEquals(grams250.getText().toString(), mDataList.get(2).getDPDiff());
        assertEquals(grams100.getText().toString(), mDataList.get(3).getDPDiff());
    }

    @After
    public void onDestroyView() {
        assertEquals(1, 1);
    }

}
