package com.fearlessspider.god.ui;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.ui.profile.ProfileFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProfileTest {
    @Test
    public void checkUsernameOnScreen() {
        FragmentScenario.launchInContainer(ProfileFragment.class);

        onView(withText("F3AR13SS")).check(matches(isDisplayed()));
    }
}
