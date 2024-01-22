package com.eventapp.wishlistservice.service;

import com.eventapp.wishlistservice.exception.EventAlreadyExistsException;
import com.eventapp.wishlistservice.exception.EventNotFoundException;
import com.eventapp.wishlistservice.model.Event;

import java.util.List;

public interface WishlistService {
    Event addEventToWishList(Event event,String username) throws EventAlreadyExistsException;

    List<Event> getAllWishListedEvents(String username) throws EventNotFoundException;

    String deleteWishListedEventById(Long favId,String username) throws EventNotFoundException;

}
