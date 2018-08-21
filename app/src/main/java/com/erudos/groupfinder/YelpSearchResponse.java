package com.erudos.groupfinder;

import java.util.ArrayList;

public class YelpSearchResponse {

    private int total;
    private ArrayList<Business> businesses;
    private int offset;


    YelpSearchResponse (){
        this.total = 0;
        this.businesses = null;
        this.offset = 0;
    }

    YelpSearchResponse (int total, ArrayList<Business> businesses){

        this.total = total;
        this.businesses = businesses;
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

    ArrayList<Business> getBusinesses() {
        return businesses;
    }
}
