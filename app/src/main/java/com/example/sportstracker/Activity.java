package com.example.sportstracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Activity {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String type;

    @TypeConverters(DataConverter.class)
    private Date timeFrom = new Date();
    private Date timeTo = new Date();

    private Date date = new Date();

    private double distance;
    private boolean feeling;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean getFeeling() {
        return feeling;
    }

    public void setFeeling(boolean feeling) {
        this.feeling = feeling;
    }


}
