package com.eventapp.wishlistservice.controller;

import com.eventapp.wishlistservice.exception.EventAlreadyExistsException;
import com.eventapp.wishlistservice.exception.EventNotFoundException;
import com.eventapp.wishlistservice.model.Event;
import com.eventapp.wishlistservice.service.WishlistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/wishlist")
@CrossOrigin
public class WishlistController {

    private static Logger logger = LoggerFactory.getLogger(WishlistController.class);

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/addEvent/{username}")
    public ResponseEntity<Event> addEvent(@RequestBody Event event,@PathVariable("username") String username) throws EventAlreadyExistsException {
        logger.info("Event created successfully");
        return new ResponseEntity<>(wishlistService.addEventToWishList(event,username), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Event>> getAllEvents(@PathVariable("username") String username) throws EventNotFoundException {
        logger.info("Retrieved all events details successfully ");
        return new ResponseEntity<>(wishlistService.getAllWishListedEvents(username),HttpStatus.OK);
    }

    @DeleteMapping("/deleteEvent/{Id}/{username}")
    public ResponseEntity<String> deleteEventById(@PathVariable("Id") Long Id,@PathVariable("username") String username) throws EventNotFoundException {
        logger.info("Event deleted successfully with given id :" + Id);
        return new ResponseEntity<>(wishlistService.deleteWishListedEventById(Id,username),HttpStatus.OK);
    }
}
