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

    @Query("SELECT * FROM steps ORDER BY id DESC LIMIT 7")
    LiveData<List<Step>> getLastEntries();

    @Query("SELECT SUM(steps) AS sum_steps FROM steps WHERE createdAt >= :start AND createdAt <= :end ORDER BY id ASC")
    Integer getStepsCount(Date start, Date end);

    @Query("SELECT SUM(steps) AS sum_steps FROM steps ORDER BY id ASC")
    Integer getTotalStepsCount();

    @Query("SELECT * FROM steps WHERE createdAt >= :start AND createdAt <= :end ORDER BY id ASC")
    LiveData<List<Step>> getStepsInRange(Date start, Date end);

    @Query("SELECT * FROM steps WHERE createdAt = :today LIMIT 1")
    Step getCurrentStep(Date today);

    @Query("SELECT steps FROM steps WHERE createdAt = :today LIMIT 1")
    Integer getCurrentStepsCount(Date today);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Step step);

    @Update
    public void updateStep(Step step);

    @Query("DELETE FROM steps")
    void deleteAll();
}
