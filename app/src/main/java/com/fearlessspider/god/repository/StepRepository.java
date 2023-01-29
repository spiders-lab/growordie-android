package com.fearlessspider.god.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.db.StepDao;

import java.util.Date;
import java.util.List;

public class StepRepository {

    private StepDao stepDao;
    private LiveData<List<Step>> stepList;

    public StepRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        stepDao = db.stepDao();
        stepList = stepDao.getAllSteps();
    }

    public Integer getStepsCount(Date start, Date end) {
        Integer steps = stepDao.getStepsCount(start, end);
        if (steps == null) {
            return 0;
        } else {
            return steps;
        }
    }

    public Integer getTotalStepsCount() {
        Integer steps = stepDao.getTotalStepsCount();
        if (steps == null) {
            return 0;
        } else {
            return steps;
        }
    }

    public LiveData<List<Step>> getStepList() {
        return stepList;
    }

    public LiveData<Step> getCurrentStep() {
        return stepDao.getCurrentStep(new Date());
    }

    public void insert(int steps) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.insert(new Step(steps));
        });
    }

    public void update(Step step) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.updateStep(step);
        });
    }

    public void deleteAll() {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.deleteAll();
        });
    }

    public void saveCurrentSteps(Integer steps) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            Step step = stepDao.getCurrentStep(new Date()).getValue();
            if (step != null) {
                step.setSteps(steps);
                stepDao.updateStep(step);
            } else {
                stepDao.insert(new Step(steps));
            }
        });
    }
}
