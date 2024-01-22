package com.eventapp.eventservice.model;

import java.util.List;

public class Event {

    Long id;
    String title;
    String type;
    Venue venue;
    List<Performer> performers;

    public Event() {
    }

    public Event(Long id, String title, String type, Venue venue,List<Performer> performers) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.venue = venue;
        this.performers = performers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
}
