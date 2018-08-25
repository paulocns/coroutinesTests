package com.psato.devcamp.data.entity;

public class ShowResponse {
    private String name;
    private double rating;

    public ShowResponse(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }
}
