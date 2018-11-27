package com.ibm.esun.esunmobilebank;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;
import tools.fastlane.screengrab.locale.LocaleTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

//@Ignore("This test will be ignored")
@RunWith(AndroidJUnit4.class)
public class TWDInterestRateActivityTest {

    @ClassRule
    public static final LocaleTestRule localeTestRule = new LocaleTestRule();
    @Rule
    public final ActivityTestRule<TWDInterestRateActivity> main = new ActivityTestRule<>(TWDInterestRateActivity.class);

    @Test
    public void screenOutput() throws InterruptedException {
        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
        Screengrab.screenshot("before_button_click");
//        onView(withId(R.id.toolbar)).check(matches(withText("hello")));
        Thread.sleep(2000);




        onView(withId(R.id.toolbar_title)).perform(click());

        Thread.sleep(2000);

        onView(withId(R.id.toolbar_title)).perform(click());

        Thread.sleep(2000);

    }
}
