package com.fearlessspider.god.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.SeekBar;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.R;
import com.fearlessspider.god.ui.profile.ProfileFragment;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will test Profile fragment
 */
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

        onView(withText("10000")).check(matches(isDisplayed()));
    }

    @Test
    public void checkSeekBarProgressOnScreen() {
        FragmentScenario.launchInContainer(ProfileFragment.class);

        onView(withClassName(Matchers.equalTo(SeekBar.class.getName()))).perform(setProgress(20000));
    }

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}
