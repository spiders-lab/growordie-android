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
    private final LiveData<List<Step>> listLiveDataLastEntries;

    public StepViewModel(Application application) {
        super(application);
        stepRepository = new StepRepository(application);
        listLiveData = stepRepository.getStepList();
        listLiveDataLastEntries = stepRepository.getLastEntries();
    }

    public LiveData<List<Step>> getAllSteps() {
        return listLiveData;
    }

    public LiveData<List<Step>> getLastEntries() {
        return listLiveDataLastEntries;
    }
    public Integer getTotalStepsCount() {
        return stepRepository.getTotalStepsCount();
    }

    public Integer getTotalWithoutToday() {
        Integer total = stepRepository.getTotalWithoutToday();
        if (total == null) return 0;
        else return total;
    }

    public Integer getDays() {
        return stepRepository.getDays();
    }

    public void saveCurrentSteps(int steps) {
        Step step = stepRepository.getCurrentStep();
        if (step == null) {
            stepRepository.insert(steps);
        } else {
            step.setEndSteps(steps);
            stepRepository.update(step);
        }
    }
}