package com.erudos.groupfinder;


import java.util.ArrayList;



class Event {

    private String imageURL;
    private String description;
    private String time;
    private String geolocation;
    private String eventLocation;
    private ArrayList<User> usersAttending = new ArrayList<>();


    //TODO: Make almost all fields optional
    Event(String description, String time, String geolocation, String imageURL, String eventLocation){

        this.description = description;
        this.time = time;
        this.geolocation = geolocation;
        this.imageURL = imageURL;
        this.eventLocation = eventLocation;
    }


    String getTime() {
        return time;
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
}
