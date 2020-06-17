package com.example.sportstracker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class SportDiff extends DiffUtil.ItemCallback<Sport> {

    @Override
    public boolean areItemsTheSame(@NonNull Sport oldItem, @NonNull Sport newItem) {
        return Objects.equals(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Sport oldItem, @NonNull Sport newItem) {
        return Objects.equals(oldItem.getResource(), newItem.getResource())
                && Objects.equals(oldItem.getSportType(), newItem.getSportType());
    }
}
