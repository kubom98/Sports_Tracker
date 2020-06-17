package com.example.sportstracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
        entities = {Activity.class}, version = 2
)

@TypeConverters(DataConverter.class)
public abstract class ActivitiesDatabase extends RoomDatabase {
    public abstract ActivityDao activityDao();

    static final Migration FROM_1_TO_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    private static volatile ActivitiesDatabase db;

    public static ActivitiesDatabase getDb(Context context) {
        ActivitiesDatabase result = db;

        if (result == null) {
            synchronized (ActivitiesDatabase.class) {
                if (db == null) {
                    db = result =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    ActivitiesDatabase.class, "activity-database")
                                    .build();
                }
            }
        }
        return result;
    }
}
