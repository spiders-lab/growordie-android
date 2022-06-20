package com.fearlessspider.god.ui.journeylist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.Journey;
import com.fearlessspider.god.repository.JourneyRepository;

import java.util.List;

/**
 * Journey List fragment model
 */
public class JourneyListViewModel extends AndroidViewModel {

    private JourneyRepository journeyRepository;
    private LiveData<List<Journey>> journeyList;

    public JourneyListViewModel(Application application) {
        super(application);

        journeyRepository = new JourneyRepository(application);
        journeyList = journeyRepository.getJourneyList();
    }

    LiveData<List<Journey>> getAll() {
        return journeyList;
    }

    void insert(Journey journey) {
        journeyRepository.insert(journey);
    }
}