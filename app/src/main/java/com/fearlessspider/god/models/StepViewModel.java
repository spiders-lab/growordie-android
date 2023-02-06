package com.fearlessspider.god.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.repository.StepRepository;

import java.util.List;

/**
 * Home fragment model
 */
public class StepViewModel extends AndroidViewModel {

    private StepRepository stepRepository;

    private final LiveData<List<Step>> listLiveData;

    public StepViewModel(Application application) {
        super(application);
        stepRepository = new StepRepository(application);
        listLiveData = stepRepository.getStepList();
    }

    public LiveData<List<Step>> getAllSteps() {
        return listLiveData;
    }

    public Integer getTotalStepsCount() {
        return stepRepository.getTotalStepsCount();
    }
}