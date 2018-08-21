package com.erudos.groupfinder;

import java.util.ArrayList;

public class YelpSearchResponse {

    private int total;
    private ArrayList<Restaurant> restaurants;
    private int offset;


    YelpSearchResponse (){
        this.total = 0;
        this.restaurants = null;
        this.offset = 0;
    }

    YelpSearchResponse (int total, ArrayList<Restaurant> restaurants){

        this.total = total;
        this.restaurants = restaurants;
        this.offset = 0;
    }

    int getOffset() {
        return offset;
    }

    void setOffset(int offset) {
        this.offset = offset;
    }

    int getTotal() {
        return total;
    }

    ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
