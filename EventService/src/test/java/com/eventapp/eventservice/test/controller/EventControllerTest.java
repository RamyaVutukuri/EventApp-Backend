package com.eventapp.eventservice.test.controller;

import com.eventapp.eventservice.controller.EventController;
import com.eventapp.eventservice.model.Event;
import com.eventapp.eventservice.model.Events;
import com.eventapp.eventservice.model.Performer;
import com.eventapp.eventservice.model.Venue;
import com.eventapp.eventservice.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    private MockMvc mockMvc;
    @Mock
    EventService eventService;
    @InjectMocks
    private EventController eventController;
    private Events events;
    private Event event;
    private Venue venue;
    private Performer performers;
    List<Performer> p = new ArrayList<>();
    List<Event> e = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        venue = new Venue();
        venue.setId(1610L);
        venue.setName("Las Vegas");
        venue.setState("NV");

        performers = new Performer();
        performers.setId(605657L);
        performers.setType("theater_family");
        performers.setName("Eiffel Tower Experience");
        p.add(performers);

        event = new Event();
        event.setId(5851039L);
        event.setTitle("Eiffel Tower Experience - Las Vegas");
        event.setType("family");
        event.setVenue(venue);
        event.setPerformers(p);
        e.add(event);
        events = new Events(e);
    }
    @AfterEach
        public void tearDown() {
        events = null;
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() throws Exception {
        when(eventService.getEvents()).thenReturn(events);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andDo(MockMvcResultHandlers.print());
        verify(eventService).getEvents();
        verify(eventService, times(1)).getEvents();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
