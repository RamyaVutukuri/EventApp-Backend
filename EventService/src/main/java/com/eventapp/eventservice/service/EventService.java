package com.eventapp.eventservice.service;

import com.eventapp.eventservice.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventService{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${eventapp.api.url}")
    private String URL;

    @Value("${eventapp.api.apikey}")
    private String API_KEY;

    public Events getEvents(){

        Events result= restTemplate.getForObject(URL+API_KEY,Events.class);
        return result;
    }
}
