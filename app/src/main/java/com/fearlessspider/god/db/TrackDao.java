package com.fearlessspider.god.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY name")
    LiveData<List<Track>> getAlphabetizedTracks();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Track track);

    @Query("DELETE FROM tracks")
    void deleteAll();
}
