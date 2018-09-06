package com.tards.imt.mypointofinterest.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date;

public class Poi implements Serializable{

    Integer id;
    String label;
    String latitude;
    String longitude;
    String description;
    Integer score;
    Date createdAt;


    public Poi(String label, String latitude, String longitude, String description, Integer score, Date createdAt) {
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.score = score;
        this.createdAt = createdAt;
    }

    public Poi(String label, String latitude, String longitude, String description) {
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;

        setCreatedAt();

    }

    public Poi(String label, String latitude, String longitude, String description,Date createdAt) {
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.createdAt = createdAt;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = Calendar.getInstance().getTime();
    }

    public String getDateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
        return fmt.format(this.getCreatedAt()) ;
    }


    @Override
    public String toString() {
        return "Poi{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", createdAt=" + createdAt +
                '}';
    }
}
