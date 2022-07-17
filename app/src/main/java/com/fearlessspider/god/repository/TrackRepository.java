package com.fearlessspider.god.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fearlessspider.god.db.GODDatabase;
import com.fearlessspider.god.db.Track;
import com.fearlessspider.god.db.TrackDao;

import java.util.List;

public class TrackRepository {

    private TrackDao trackDao;
    private LiveData<List<Track>> trackList;

    public TrackRepository(Application application) {
        GODDatabase db = GODDatabase.getDatabase(application);
        trackDao = db.trackDao();
        trackList = trackDao.getAlphabetizedTracks();
    }

    public LiveData<List<Track>> getTrackList() {
        return trackList;
    }

    public void insert(Track track) {
        GODDatabase.databaseWriteExecutor.execute(() -> {
            trackDao.insert(track);
        });
    }
}
