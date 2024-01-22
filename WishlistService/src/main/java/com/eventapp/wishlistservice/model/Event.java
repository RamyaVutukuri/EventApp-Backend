package com.eventapp.wishlistservice.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="events")
public class Event {

    @Id
    Long id;
    String username;
    String title;
    String type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ev_Id",referencedColumnName = "id")
    Venue venue;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ep_Id",referencedColumnName = "id")
    List<Performer> performers;

    public Event() {
    }

    public Event(Long id, String username, String title, String type, Venue venue, List<Performer> performers) {
        this.id = id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
