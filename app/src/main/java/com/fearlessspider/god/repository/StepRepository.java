package com.fearlessspider.god.repository;

import android.app.Application;

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

    public Integer getSteps(Date start, Date end) {
        return stepDao.getSteps(start, end);
    }
    public LiveData<List<Step>> getStepList() {
        return stepList;
    }

    public LiveData<Step> getCurrentSteps() {
        return stepDao.getCurrentSteps(new Date());
    }

    public void insert(int steps) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.insert(new Step(0, steps));
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

    }
}
