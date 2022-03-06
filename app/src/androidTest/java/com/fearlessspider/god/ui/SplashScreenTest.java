package com.fearlessspider.god.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.ui.splashscreen.SplashScreenFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will test SplashScreen activity
 */
@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {
    @Rule
    public ActivityScenarioRule<SplashScreenFragment> activityScenarioRule = new ActivityScenarioRule(SplashScreenFragment.class);

    @Test
    public void checkTextOnScree() {
        onView(withText("G.O.D.")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
