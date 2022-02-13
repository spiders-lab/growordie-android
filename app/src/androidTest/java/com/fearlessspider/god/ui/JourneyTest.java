package com.fearlessspider.god.ui;

import androidx.fragment.app.testing.FragmenScenario;

import com.fearlessspider.god.ui.journey.JourneyFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class JourneyTest {
    @Test
    public void checkTitleOfScreen() {
        FragmentScenario.launchInContainer(JourneyFragment.class);
    }
}
