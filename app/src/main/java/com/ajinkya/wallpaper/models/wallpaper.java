package com.ajinkya.wallpaper.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class wallpaper implements Serializable {
    String Category,Image,Thumbnail;
    boolean Trending;
    transient Timestamp Timestamp;

    public wallpaper() {
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public boolean isTrending() {
        return Trending;
    }

    public void setTrending(boolean trending) {
        Trending = trending;
    }

    public com.google.firebase.Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp = timestamp;
    }
}
