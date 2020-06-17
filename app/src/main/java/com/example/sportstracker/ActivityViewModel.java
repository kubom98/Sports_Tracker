package com.example.sportstracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ActivityViewModel extends AndroidViewModel {

    private LiveData<List<Activity>> activities;

    private ActivityDao activityDao;

    private ActivitiesDatabase db;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        db = ActivitiesDatabase.getDb(application);
        activityDao = db.activityDao();
        activities = activityDao.listOfAllActivities();
    }

    public LiveData<List<Activity>> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        db.getTransactionExecutor().execute(() -> activityDao.saveActivity(activity));
    }

//    public void deleteActvity(Activity position){
//        ArrayList<Activity> newValue = new ArrayList<>(activities.getValue());
//        newValue.remove(position);
//        activities.postValue(newValue);
//    }

    public void deleteActivity(Activity activity) {
        db.getTransactionExecutor().execute(() -> activityDao.deleteActivity(activity));
    }

    public void updateActivity(Activity activity) {
        db.getTransactionExecutor().execute(() -> activityDao.updateActivity(activity));
    }
}
