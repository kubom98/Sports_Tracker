package com.example.sportstracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.*;

import java.util.List;


@Dao
interface ActivityDao {

    @Query("SELECT * FROM activity")
    LiveData<List<Activity>> listOfAllActivities();

    @Insert
    void saveActivity(Activity activity);

    @Update
    void updateActivity(Activity activity);

    @Delete
    void deleteActivity(Activity activity);

}
