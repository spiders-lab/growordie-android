package com.fearlessspider.god.ui.exerciselist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.Exercise;
import com.fearlessspider.god.repository.ExerciseRepository;

import java.util.List;

/**
 * Exercise List fragment model
 */
public class ExerciseListViewModel extends AndroidViewModel {

    private ExerciseRepository exerciseRepository;
    private LiveData<List<Exercise>> exerciseList;

    public ExerciseListViewModel(Application application) {
        super(application);

        exerciseRepository = new ExerciseRepository(application);
        exerciseList = exerciseRepository.getExerciseList();
    }

    LiveData<List<Exercise>> getAll() {
        return exerciseList;
    }

    void insert(Exercise exercise) {
        exerciseRepository.insert(exercise);
    }
}