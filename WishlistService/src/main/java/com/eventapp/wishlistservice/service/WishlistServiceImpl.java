package com.eventapp.wishlistservice.service;

import com.eventapp.wishlistservice.exception.EventAlreadyExistsException;
import com.eventapp.wishlistservice.exception.EventNotFoundException;
import com.eventapp.wishlistservice.model.Event;
import com.eventapp.wishlistservice.model.Performer;

import com.eventapp.wishlistservice.repository.EventRepository;
import com.eventapp.wishlistservice.repository.PerformerRepository;
import com.eventapp.wishlistservice.repository.VenueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    VenueRepository venueRepository;
    @Autowired
    PerformerRepository performerRepository;

    public Event addEventToWishList(Event event,String username) throws EventAlreadyExistsException {
        Optional<Event> events = eventRepository.findByIdAndUsername(event.getId(),username);
        if (events.isPresent()) {
            throw new EventAlreadyExistsException("Event already present in wishlist");
        }
        venueRepository.save(event.getVenue());
        for(Performer p : event.getPerformers()) {
            performerRepository.save(p);
        }
         return eventRepository.save(event);
    }

    public List<Event> getAllWishListedEvents(String username) throws EventNotFoundException {
        List<Event> events = eventRepository.findByUsername(username);
        if (!events.isEmpty()) {
            return eventRepository.findByUsername(username);
        }
        else {
            throw new EventNotFoundException("No Events present in wishlist");
        }
    }

    public String deleteWishListedEventById(Long id,String username) throws EventNotFoundException {
        Optional<Event> wishList = eventRepository.findByIdAndUsername(id,username);
        if(wishList.isPresent()){
            eventRepository.deleteById(id);
            return "Event Deleted";
        }
        else{
            throw new EventNotFoundException("Event not found with given");
        }
    }
}


