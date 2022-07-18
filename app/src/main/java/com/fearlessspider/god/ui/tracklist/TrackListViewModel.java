package com.fearlessspider.god.ui.tracklist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.Track;
import com.fearlessspider.god.repository.TrackRepository;

import java.util.List;

/**
 * Track List fragment model
 */
public class TrackListViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;
    private LiveData<List<Track>> trackList;

    public TrackListViewModel(Application application) {
        super(application);

        trackRepository = new TrackRepository(application);
        trackList = trackRepository.getTrackList();
    }

    LiveData<List<Track>> getAll() {
        return trackList;
    }

    void insert(Track journey) {
        trackRepository.insert(journey);
    }
}