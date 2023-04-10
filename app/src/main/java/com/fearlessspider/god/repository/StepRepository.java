package com.fearlessspider.god.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.db.StepDao;
import com.fearlessspider.god.utils.DateUtil;
import com.fearlessspider.god.utils.Logger;

import java.util.Date;
import java.util.List;

public class StepRepository {

    private StepDao stepDao;
    private LiveData<List<Step>> stepList;
    private LiveData<List<Step>> lastEntries;

    public StepRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        stepDao = db.stepDao();
        stepList = stepDao.getAllSteps();
        lastEntries = stepDao.getLastEntries();
    }

    public Integer getStepsCount(Date start, Date end) {
        Integer steps = stepDao.getStepsCount(start, end);
        if (steps == null) {
            return 0;
        } else {
            return steps;
        }
    }

    public Integer getCurrentStepsCount() {
        return stepDao.getCurrentStepsCount(new Date(DateUtil.getToday()));
    }

    public Integer getTotalWithoutToday() {
        return stepDao.getTotalToDate(new Date(DateUtil.getToday()));
    }

    public Integer getDays() {
        return stepDao.getEntriesCount();
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

    public LiveData<List<Step>> getLastEntries() {
        return lastEntries;
    }
    public Step getCurrentStep() {
        return stepDao.getCurrentStep(new Date(DateUtil.getToday()));
    }

    public void insert(int steps) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.insert(new Step(steps, steps));
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
}
