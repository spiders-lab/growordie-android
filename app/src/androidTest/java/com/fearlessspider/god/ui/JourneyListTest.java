package com.fearlessspider.god.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.R;
import com.fearlessspider.god.ui.journey.JourneyFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will test Journey List fragment
 */
@RunWith(AndroidJUnit4.class)
public class JourneyListTest {
    @Test
    public void checkTitleOfScreen() {
        FragmentScenario.launchInContainer(JourneyListFragment.class);

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.journey_list)).check(matches(withParent(withId(R.id.toolbar))));
    }
}
