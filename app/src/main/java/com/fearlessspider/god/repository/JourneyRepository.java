package com.fearlessspider.god.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Journey;
import com.fearlessspider.god.db.JourneyDao;

import java.util.List;

public class JourneyRepository {

    private JourneyDao journeyDao;
    private LiveData<List<Journey>> journeyList;

    public JourneyRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        journeyDao = db.journeyDao();
        journeyList = journeyDao.getAlphabetizedJourneys();
    }

    public LiveData<List<Journey>> getJourneyList() {
        return journeyList;
    }

    public void insert(Journey journey) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            journeyDao.insert(journey);
        });
    }
}
