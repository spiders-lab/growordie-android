package com.fearlessspider.god.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.db.StepDao;

import java.util.List;

public class StepRepository {

    private StepDao stepDao;
    private LiveData<List<Step>> stepList;

    public StepRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        stepDao = db.stepDao();
        stepList =stepDao.getSteps();
    }

    public LiveData<List<Step>> getStepList() {
        return stepList;
    }

    public void insert(Step step) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            stepDao.insert(step);
        });
    }
}
