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
public class UserDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserDao userDao;
    private GODDatabase godDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        godDatabase = Room.inMemoryDatabaseBuilder(context, GODDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        userDao = godDatabase.userDao();
    }

    @After
    public void closeDb() {
        godDatabase.close();
    }

    @Test
    public void insertAndGetUser() throws Exception {
        User user = new User("Tester");
        userDao.insert(user);
        List<User> allUsers = LiveDataTestUtil.getValue(userDao.getAlphabetizedUsers());
        Assert.assertEquals(allUsers.get(0).getUsername(), user.getUsername());
    }

    @Test
    public void getAllUsers() throws Exception {
        User user = new User("aaa");
        userDao.insert(user);
        User user2 = new User("bbb");
        userDao.insert(user2);
        List<User> allUsers = LiveDataTestUtil.getValue(userDao.getAlphabetizedUsers());
        Assert.assertEquals(allUsers.get(0).getUsername(), user.getUsername());
        Assert.assertEquals(allUsers.get(1).getUsername(), user2.getUsername());
    }

    @Test
    public void deleteAll() throws Exception {
        User user = new User("word");
        userDao.insert(user);
        User user2 = new User("word2");
        userDao.insert(user2);
        userDao.deleteAll();
        List<User> allUsers = LiveDataTestUtil.getValue(userDao.getAlphabetizedUsers());
        Assert.assertTrue(allUsers.isEmpty());
    }
}