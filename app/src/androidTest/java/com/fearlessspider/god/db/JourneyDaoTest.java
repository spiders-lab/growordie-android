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
public class JourneyDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private JourneyDao journeyDao;
    private GODDatabase godDatabase;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();

        godDatabase = Room.inMemoryDatabaseBuilder(context, GODDatabase.class)
                .allowMainThreadQueries()
                .build();
        journeyDao = godDatabase.journeyDao();
    }

    @After
    public void closeDb() {
        godDatabase.close();
    }

    @Test
    public void insertAndGetJourney() throws Exception {
        Journey journey = new Journey(0, "Programming");
        journeyDao.insert(journey);
        List<Journey> allJourneys = LiveDataTestUtil.getValue(journeyDao.getAlphabetizedJourneys());
        Assert.assertEquals(allJourneys.get(0).getName(), journey.getName());
    }

    @Test
    public void getAllJourneys() throws Exception {
        Journey journey = new Journey(0, "Health");
        journeyDao.insert(journey);
        Journey journey2 = new Journey(0, "Books");
        journeyDao.insert(journey2);
        List<Journey> allJourneys = LiveDataTestUtil.getValue(journeyDao.getAlphabetizedJourneys());
        Assert.assertEquals(allJourneys.get(0).getName(), journey.getName());
        Assert.assertEquals(allJourneys.get(1).getName(), journey2.getName());
    }

    @Test
    public void deleteAll() throws Exception {
        Journey journey = new Journey(0, "Aaaa");
        journeyDao.insert(journey);
        Journey journey1 = new Journey(0, "Bbbb");
        journeyDao.insert(journey1);
        journeyDao.deleteAll();
        List<Journey> allJourneys = LiveDataTestUtil.getValue(journeyDao.getAlphabetizedJourneys());
        Assert.assertTrue(allJourneys.isEmpty());
    }
}
