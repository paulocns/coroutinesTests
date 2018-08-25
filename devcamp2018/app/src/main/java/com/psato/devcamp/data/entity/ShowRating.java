package com.psato.devcamp.data.entity;

import com.google.gson.annotations.SerializedName;

public class ShowRating {
    @SerializedName("rating")
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
