package com.example.sportstracker;

import androidx.annotation.NonNull;

import java.util.Objects;

class ActivityDiff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<Activity> {

    @Override
    public boolean areItemsTheSame(@NonNull Activity oldItem, @NonNull Activity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Activity oldItem, @NonNull Activity newItem) {
        return Objects.equals(oldItem.getType(), newItem.getType())
                && Objects.equals(oldItem.getTimeFrom(), newItem.getTimeFrom())
                && Objects.equals(oldItem.getTimeTo(), newItem.getTimeTo())
                && Objects.equals(oldItem.getFeeling(), newItem.getFeeling())
                && Objects.equals(oldItem.getDistance(), newItem.getDistance());
    }

}
