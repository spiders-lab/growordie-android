package com.fearlessspider.god.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StepDao {
    @Query("SELECT * FROM steps ORDER BY id ASC")
    LiveData<List<Step>> getSteps();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Step step);

    @Query("DELETE FROM steps")
    void deleteAll();
}
