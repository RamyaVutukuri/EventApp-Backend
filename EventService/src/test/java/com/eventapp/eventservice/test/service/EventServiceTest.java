package com.eventapp.eventservice.test.service;

import com.eventapp.eventservice.model.Event;
import com.eventapp.eventservice.model.Events;
import com.eventapp.eventservice.model.Performer;
import com.eventapp.eventservice.model.Venue;
import com.eventapp.eventservice.service.EventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @InjectMocks
    EventService eventService;
    private Events events;
    private Event event;
    private Venue venue;
    private Performer performers;
    List<Performer> p = new ArrayList<>();
    List<Event> e = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        venue =new Venue(1610L,"Las Vegas","NV");
        performers =new Performer(605657L,"theater_family","Eiffel Tower Experience");
        p.add(performers);
        event =new Event(5851039L,"Eiffel Tower Experience - Las Vegas","family",venue,p);
        e.add(event);
        events = new Events(e);
    }

    @AfterEach
    public void tearDown() {
        events = null;
    }

    @Test
    public void givenGetAllEventsThenShouldReturnListOfAllEvents() {
        List<Event> results = events.getEvents();
        assertEquals(e,results);
    }
}