package com.fearlessspider.god.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface StepDao {
    @Query("SELECT * FROM steps ORDER BY id ASC")
    LiveData<List<Step>> getAllSteps();

    @Query("SELECT SUM(steps) FROM steps WHERE createdAt >= :start AND createdAt <= :end ORDER BY id ASC")
    LiveData<List<Step>> getSteps(Date start, Date end);

    @Query("SELECT * FROM steps WHERE steps > 0 AND createdAt < :today ORDER BY id ASC")
    LiveData<List<Step>> getStepsWithoutToday(Date today);

    @Query("SELECT * FROM steps WHERE createdAt = :today LIMIT 1")
    LiveData<Step> getCurrentSteps(Date today);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Step step);

    @Update
    public void updateStep(Step step);

    @Query("DELETE FROM steps")
    void deleteAll();
}
