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
import com.fearlessspider.god.ui.profile.ProfileFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProfileTest {

    @Test
    public void checkTitleOfScreen() {
        FragmentScenario.launchInContainer(ProfileFragment.class);

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.profile)).check(matches(withParent(withId(R.id.toolbar))));
    }
    @Test
    public void checkUsernameOnScreen() {
        FragmentScenario.launchInContainer(ProfileFragment.class);

        onView(withText("F3AR13SS")).check(matches(isDisplayed()));
    }
}
