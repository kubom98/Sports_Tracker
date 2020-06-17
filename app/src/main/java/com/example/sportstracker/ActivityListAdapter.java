package com.example.sportstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityListAdapter extends ListAdapter<Activity, ActivityViewHolder> {

    private SportOnClickedListener sportOnClickedListener;

    List<Activity> activities = new ArrayList<>();

    public ActivityListAdapter( SportOnClickedListener sportOnClickedListener) {
        super(new ActivityDiff());
        this.sportOnClickedListener = sportOnClickedListener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layout = R.layout.activity_from_list;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        //activities.add(getItem(position));
        holder.setActivity(getItem(position), sportOnClickedListener);
    }

   // public Activity getItem(int position){
   //     return activities.get(position);
   // }
}
