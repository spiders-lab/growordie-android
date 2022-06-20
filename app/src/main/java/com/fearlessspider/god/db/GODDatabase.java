package com.fearlessspider.god.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Journey.class, Track.class, Exercise.class, Step.class}, version = 1, exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class GODDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract JourneyDao journeyDao();
    public abstract TrackDao trackDao();
    public abstract ExerciseDao exerciseDao();
    public abstract StepDao stepDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile GODDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static GODDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GODDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GODDatabase.class, "god_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UserDao userDao = INSTANCE.userDao();
                userDao.deleteAll();
            });
        }
    };
}