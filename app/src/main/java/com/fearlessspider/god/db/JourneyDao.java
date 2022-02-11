package com.fearlessspider.god.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JourneyDao {
    @Query("SELECT * FROM journeys ORDER BY id ASC")
    LiveData<List<Journey>> getAlphabetizedJourneys();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Journey journey);

    @Query("DELETE FROM journeys")
    void deleteAll();
}
