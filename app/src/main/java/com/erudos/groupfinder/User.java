package com.erudos.groupfinder;

import java.util.ArrayList;

class User {

    private String userName;
    private String realName;
    private ArrayList<String> interests = new ArrayList<>();
    private ArrayList<Event> previousEvents = new ArrayList<>();
    private ArrayList<Event> attendingEvents = new ArrayList<>();

    User(String realName, String userName){

        this.realName = realName;
        this.userName = userName;
    }

    String getUserName(){
        return userName;
    }

    ArrayList<Event> getPreviousEvents() {
        return previousEvents;
    }

    ArrayList<Event> getAttendingEvents() {
        return attendingEvents;
    }

    String getRealName() {
        return realName;
    }

    ArrayList<String> getInterests(){
        return interests;
    }

    boolean containsInterest(String interest){
        return interests.contains(interest);
    }

    void addInterest(String interest){
        interests.add(interest);
    }

    void removeInterest(String interest){
        interests.remove(interest);
    }

    void addEvent(Event event){
        attendingEvents.add(event);
    }

    void removeEvent(Event event){
        attendingEvents.remove(event);
    }

}

