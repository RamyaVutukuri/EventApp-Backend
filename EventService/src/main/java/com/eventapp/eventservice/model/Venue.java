package com.eventapp.eventservice.model;

public class Venue {
    Long id;
    String name;
    String state;

    public Venue() {
    }

    public Venue(Long id, String name,String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
