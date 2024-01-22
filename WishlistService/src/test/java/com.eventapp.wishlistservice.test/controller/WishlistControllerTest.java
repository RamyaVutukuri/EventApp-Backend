package com.eventapp.wishlistservice.test.controller;

import com.eventapp.wishlistservice.controller.WishlistController;
import com.eventapp.wishlistservice.model.Event;
import com.eventapp.wishlistservice.model.Events;
import com.eventapp.wishlistservice.model.Performer;
import com.eventapp.wishlistservice.model.Venue;
import com.eventapp.wishlistservice.service.WishlistService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class WishlistControllerTest {

    private MockMvc mockMvc;
    @Mock
    WishlistService wishlistService;
    @InjectMocks
    private WishlistController wishlistController;
    private Events events;
    private Event event;
    private Venue venue;
    private Performer performers;
    List<Performer> p = new ArrayList<>();
    List<Event> e = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
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
        event.setUsername("ramya");
        event.setTitle("Eiffel Tower Experience - Las Vegas");
        event.setType("Concert");
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
    public void givenEventToSaveThenShouldReturnSavedWishList() throws Exception {
        when(wishlistService.addEventToWishList(any(),eq("ramya"))).thenReturn(event);
        mockMvc.perform(post("/api/v1/wishlist/addEvent/ramya")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(wishlistService).addEventToWishList(any(),eq("ramya"));
    }

    @Test
    public void givenGetAllWishListThenShouldReturnListOfAllWishList() throws Exception {
        when(wishlistService.getAllWishListedEvents("ramya")).thenReturn(e);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wishlist/ramya")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(event)))
                .andDo(MockMvcResultHandlers.print());
        verify(wishlistService).getAllWishListedEvents("ramya");
        verify(wishlistService, times(1)).getAllWishListedEvents("ramya");

    }

    @Test
    public void givenWishlistIdToDeleteThenShouldNotReturnDeletedEvent() throws Exception {
        when(wishlistService.deleteWishListedEventById(event.getId(),event.getUsername())).thenReturn("Event Deleted");
        mockMvc.perform(delete("/api/v1/wishlist/deleteEvent/5851039/ramya")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(event)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
