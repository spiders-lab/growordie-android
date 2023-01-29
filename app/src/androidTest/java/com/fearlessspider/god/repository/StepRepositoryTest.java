package com.fearlessspider.god.repository;

import static org.mockito.Mockito.mock;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.utils.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StepRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Observer<List<Step>> stepListObserver = mock(Observer.class);

    private static final int FAKE_STEP_ID = 123;

    private StepRepository stepRepository;

    @Before
    public void setUp() {
        stepRepository = new StepRepository(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void getStepList() {
        List<Step> stepsList = new ArrayList<>();
        stepsList.add(new Step(FAKE_STEP_ID, 10000));

        stepRepository.getStepList().observeForever(stepListObserver);
        stepRepository.insert(10000);

        Logger.log("steps"+ stepRepository.getStepList());
        Assert.assertEquals(stepRepository.getStepList(), stepsList);
    }
}