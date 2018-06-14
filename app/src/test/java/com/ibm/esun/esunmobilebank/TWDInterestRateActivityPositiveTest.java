package com.ibm.esun.esunmobilebank;


import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class TWDInterestRateActivityPositiveTest {

    private Activity activity = Robolectric.buildActivity(TWDInterestRateActivity.class).create().get();

    @Test
    public void menuClick() {


    }
}
