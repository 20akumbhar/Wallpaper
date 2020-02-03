package com.ajinkya.wallpaper.models;

import com.google.firebase.Timestamp;

public class category {
    String Name,Thumbnail;
    Timestamp Timestamp;
    double count;

    public category() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public com.google.firebase.Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp = timestamp;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
