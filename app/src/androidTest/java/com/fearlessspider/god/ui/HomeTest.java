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
import com.fearlessspider.god.ui.home.HomeFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will test Home fragment
 *
 * A test using the androidx.test unified API, which can execute on an Android device or locally using Robolectric.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 */
@RunWith(AndroidJUnit4.class)
public class HomeTest {
    @Test
    public void checkStepOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withText("steps")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkToolbarTitleOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText("TODAY")).check(matches(withParent(withId(R.id.toolbar))));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfProgressBarOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfGraphOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.graph)).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfStepsInitialOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.stepsinpiechart)).check(matches(isDisplayed()));
        onView(withText("0")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfStepsUnitOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.unit)).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfAverageAndTotalOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withId(R.id.averageandtotal)).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CheckExerciseCounterOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withText("Exercises")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CheckTrackCounterOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withText("Tracks")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CheckJourneyCounterOnScreen() {
        FragmentScenario.launchInContainer(HomeFragment.class);

        onView(withText("Journeys")).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
