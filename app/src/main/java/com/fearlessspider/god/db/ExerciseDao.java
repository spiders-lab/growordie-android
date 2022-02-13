package com.fearlessspider.god.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercises ORDER BY createdAt")
    LiveData<List<Exercise>> getOrderedByCreatedAt();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Exercise exercise);

    @Query("DELETE FROM exercises")
    void deleteAll();
}
