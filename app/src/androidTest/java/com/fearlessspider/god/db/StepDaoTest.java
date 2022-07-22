package com.fearlessspider.god.db;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.fearlessspider.god.LiveDataTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StepDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private StepDao stepDao;
    private GODDatabase godDatabase;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();

        godDatabase = Room.inMemoryDatabaseBuilder(context, GODDatabase.class)
                .allowMainThreadQueries()
                .build();
        stepDao = godDatabase.stepDao();
    }

    @After
    public void closeDb() {
        godDatabase.close();
    }

    @Test
    public void insertAndGetStep() throws Exception {
        Step step = new Step(0, 1);
        stepDao.insert(step);
        List<Step> allSteps = LiveDataTestUtil.getValue(stepDao.getAllSteps());
        Assert.assertEquals(allSteps.get(0).getCreatedAt(), step.getCreatedAt());
    }

    @Test
    public void getAllSteps() throws Exception {
        Step step = new Step(0, 1);
        stepDao.insert(step);
        Step step1 = new Step(1, 2);
        stepDao.insert(step1);
        List<Step> allStep = LiveDataTestUtil.getValue(stepDao.getAllSteps());
        Assert.assertEquals(allStep.get(0).getCreatedAt(), step.getCreatedAt());
        Assert.assertEquals(allStep.get(1).getCreatedAt(), step1.getCreatedAt());
    }

    @Test
    public void deleteAll() throws Exception {
        Step step = new Step(0, 1);
        stepDao.insert(step);
        Step step1 = new Step(1, 2);
        stepDao.insert(step1);
        stepDao.deleteAll();
        List<Step> allSteps = LiveDataTestUtil.getValue(stepDao.getAllSteps());
        Assert.assertTrue(allSteps.isEmpty());
    }
}
