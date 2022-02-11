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
public class TrackDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TrackDao trackDao;
    private GODDatabase godDatabase;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();

        godDatabase = Room.inMemoryDatabaseBuilder(context, GODDatabase.class)
                .allowMainThreadQueries()
                .build();
        trackDao = godDatabase.trackDao();
    }

    @After
    public void closeDb() {
        godDatabase.close();
    }

    @Test
    public void insertAndGetTrack() throws Exception {
        Track track = new Track(0, "Java");
        trackDao.insert(track);
        List<Track> allTracks = LiveDataTestUtil.getValue(trackDao.getAlphabetizedTracks());
        Assert.assertEquals(allTracks.get(0).getName(), track.getName());
    }

    @Test
    public void getAllTracks() throws Exception {
        Track track = new Track(0, "C++");
        trackDao.insert(track);
        Track track1 = new Track(0, "Python");
        trackDao.insert(track1);
        List<Track> allTracks = LiveDataTestUtil.getValue(trackDao.getAlphabetizedTracks());
        Assert.assertEquals(allTracks.get(0).getName(), track.getName());
        Assert.assertEquals(allTracks.get(1).getName(), track1.getName());
    }

    @Test
    public void deleteAll() throws Exception {
        Track track = new Track(0, "JavaScript");
        trackDao.insert(track);
        Track track1 = new Track(0, "HTML");
        trackDao.insert(track1);
        trackDao.deleteAll();
        List<Track> allTracks = LiveDataTestUtil.getValue(trackDao.getAlphabetizedTracks());
        Assert.assertTrue(allTracks.isEmpty());
    }
}
