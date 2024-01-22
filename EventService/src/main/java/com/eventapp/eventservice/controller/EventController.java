package com.eventapp.eventservice.controller;

import com.eventapp.eventservice.model.Events;
import com.eventapp.eventservice.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
public class EventController {

    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity<Events> getAllEvents(){
            Events results = eventService.getEvents();
            logger.info("Retrieved list of events from third party api successfully");
            return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
