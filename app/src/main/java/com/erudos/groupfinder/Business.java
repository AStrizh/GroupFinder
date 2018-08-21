package com.erudos.groupfinder;

import java.util.ArrayList;

public class Business {
    private String id;
    private String name;
    private String phone;
    private String website;
    private double rating;
    String price;
    int reviewCount;
    private String imageUrl;
    private ArrayList<String> address;
    ArrayList<String> photos;
    private double latitude;
    private double longitude;
    Boolean openNow;


    Business(){}

    Business(String id, String name, String phone, String imageUrl, String website, String price, int reviewCount,
             double rating, boolean openNow, ArrayList<String> photos){

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.website = website;
        this.price = price;
        this.reviewCount = reviewCount;
        this.photos = photos;
        this.openNow = openNow;
        this.rating = rating;
    }

    Business(String id, String name, String phone, String imageUrl, ArrayList<String> address) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    String getId() {
        return id;
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
}