package com.erudos.groupfinder;


import java.util.ArrayList;



class Event {

    private String eventName;
    private String imageURL;
    private String description;
    private String time;
    private String geolocation;
    private String eventLocation;
    private ArrayList<User> usersAttending = new ArrayList<>();
    private Business businessLocation;


    Event(String name, String description, String time, Business business){

        this.eventName = name;
        this.description = description;
        this.time = time;
        this.businessLocation = business;
    }


    String getTime() {
        return time;
    }

    public String getEventName() {
        return eventName;
    }

    void setTime(String time){
        this.time = time;
    }

    String getImageURL() {
        return imageURL;
    }

    void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    String getGeolocation() {
        return geolocation;
    }

    void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    String getEventLocation() {
        return eventLocation;
    }

    void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    String getDescription() {
        return description;
    }

    ArrayList<User> getUsersAttending() {
        return usersAttending;
    }

    void addUser(User user){
        usersAttending.add(user);
    }

    void removeUser(User user){
        usersAttending.remove(user);
    }

    public Business getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(Business businessLocation) {
        this.businessLocation = businessLocation;
    }
}
