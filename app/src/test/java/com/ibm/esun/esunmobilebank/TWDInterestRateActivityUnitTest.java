package com.ibm.esun.esunmobilebank;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;


@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class TWDInterestRateActivityUnitTest {

    private TWDInterestRateActivity twdInterestRateActivity;
    private String depositTitle = "臺幣存款利率";

    private ActivityController<TWDInterestRateActivity> activityController;

    @Before
    public void setUp() throws Exception {
        twdInterestRateActivity = Robolectric.setupActivity(TWDInterestRateActivity.class);

        activityController = Robolectric.buildActivity(TWDInterestRateActivity.class)
                .create().start();
        Activity activity = activityController.get();
    }

    @Test
    public void testActivity() {
        assertThat(twdInterestRateActivity).isNotNull();
        assertThat(depositTitle).isEqualTo(twdInterestRateActivity.getTitle());
    }



}
