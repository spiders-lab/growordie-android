package com.fearlessspider.god.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Exercise;
import com.fearlessspider.god.db.ExerciseDao;

import java.util.List;

public class ExerciseRepository {

    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exerciseList;

    public ExerciseRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        exerciseList = exerciseDao.getOrderedByCreatedAt();
    }

    public LiveData<List<Exercise>> getExerciseList() {
        return exerciseList;
    }

    public void insert(Exercise exercise) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            exerciseDao.insert(exercise);
        });
    }
}
