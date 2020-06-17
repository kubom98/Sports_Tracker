package com.example.sportstracker;

import androidx.annotation.DrawableRes;

import java.io.Serializable;
import java.util.Objects;

public class Sport implements Serializable {

    private String sportType;

    @DrawableRes
    private int resource;

    public Sport(String sport, @DrawableRes int resource) {
        this.sportType = sport;
        this.resource = resource;
    }

    @DrawableRes
    public int getResource() {
        return resource;
    }

    public String getSportType() {
        return sportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return resource == sport.resource &&
                this.sportType.equals(sport.sportType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportType, resource);
    }
}
