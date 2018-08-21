package com.erudos.groupfinder;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String phone;
    private String website;
    private double rating;
    private String imageUrl;
    private ArrayList<String> address;
    private double latitude;
    private double longitude;
    private ArrayList<String> categories;

    Restaurant(String name, String phone, String website,
                      double rating, String imageUrl, ArrayList<String> address,
                      double latitude, double longitude, ArrayList<String> categories) {

        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    String getWebsite() {
        return  website;
    }

    double getRating() {
        return rating;
    }

    String getImageUrl(){
        return imageUrl;
    }

    ArrayList<String> getAddress() {
        return address;
    }

    double getLatitude() {
        return latitude;
    }

    double getLongitude() {
        return longitude;
    }

    ArrayList<String> getCategories() {
        return categories;
    }
}