package com.fearlessspider.god.repository;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.db.StepDao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.reactivex.rxjava3.core.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StepRepositoryTest {
    private static final int FAKE_STEP_ID = 123;

    private StepRepository stepRepository;

    @Mock private Application mockApplication;
    @Mock private Context mockContext;
    @Mock private StepRepository mockStepRepository;
    @Mock private StepDao mockStepDao;
    @Mock private Step mockStep;

    @Before
    public void setUp() {
        mockApplication = mock(Application.class);
        mockContext = mock(Context.class);
        Mockito.when(mockApplication.getApplicationContext()).thenReturn(mockContext);
        stepRepository = new StepRepository(mockApplication);
    }

    @Test
    public void getStepList() {
        List<Step> stepsList = new ArrayList<>();
        stepsList.add(new Step(FAKE_STEP_ID, 10000));
        stepRepository.insert(new Step(FAKE_STEP_ID, 10000));
        given(stepRepository.getStepList()).willReturn(Observable.just(stepsList));
    }
}