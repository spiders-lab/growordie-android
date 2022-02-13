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
public class ExerciseDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ExerciseDao exerciseDao;
    private GODDatabase godDatabase;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();

        godDatabase = Room.inMemoryDatabaseBuilder(context, GODDatabase.class)
                .allowMainThreadQueries()
                .build();
        exerciseDao = godDatabase.exerciseDao();
    }

    @After
    public void closeDb() {
        godDatabase.close();
    }

    @Test
    public void insertAndGetExercise() throws Exception {
        Exercise exercise = new Exercise(0, "40 lines of code");
        exerciseDao.insert(exercise);
        List<Exercise> allExercise = LiveDataTestUtil.getValue(exerciseDao.getOrderedByCreatedAt());
        Assert.assertEquals(allExercise.get(0).getCreatedAt(), exercise.getCreatedAt());
    }

    @Test
    public void getAllExercises() throws Exception {
        Exercise exercise = new Exercise(0, "10 lines of code");
        exerciseDao.insert(exercise);
        Exercise exercise1 = new Exercise(0, "20 lines of code");
        exerciseDao.insert(exercise1);
        List<Exercise> allExercise = LiveDataTestUtil.getValue(exerciseDao.getOrderedByCreatedAt());
        Assert.assertEquals(allExercise.get(0).getCreatedAt(), exercise.getCreatedAt());
        Assert.assertEquals(allExercise.get(0).getCreatedAt(), exercise1.getCreatedAt());
    }

    @Test
    public void deleteAll() throws Exception {
        Exercise exercise = new Exercise(0, "30 lines of code");
        exerciseDao.insert(exercise);
        Exercise exercise1 = new Exercise(0, "50 lines of code");
        exerciseDao.insert(exercise1);
        exerciseDao.deleteAll();
        List<Exercise> allExercise = LiveDataTestUtil.getValue(exerciseDao.getOrderedByCreatedAt());
        Assert.assertTrue(allExercise.isEmpty());
    }
}
