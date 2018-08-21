package com.erudos.groupfinder;

import java.util.ArrayList;

public class YelpSearchResponse {

    private int total;
    private ArrayList<Restaurant> restaurants;
    private int offset;

    public YelpSearchResponse (int total, ArrayList<Restaurant> restaurants){

        this.total = total;
        this.restaurants = restaurants;
        this.offset = 0;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
