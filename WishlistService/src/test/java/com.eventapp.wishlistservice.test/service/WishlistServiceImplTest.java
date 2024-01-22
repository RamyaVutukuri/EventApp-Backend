package com.eventapp.wishlistservice.test.service;

import com.eventapp.wishlistservice.exception.EventNotFoundException;
import com.eventapp.wishlistservice.model.Event;
import com.eventapp.wishlistservice.model.Events;
import com.eventapp.wishlistservice.model.Performer;
import com.eventapp.wishlistservice.model.Venue;
import com.eventapp.wishlistservice.repository.EventRepository;
import com.eventapp.wishlistservice.service.WishlistServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class WishlistServiceImplTest {
    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private WishlistServiceImpl wishlistService;
    private Event event;
    private Events events;
    private Venue venue;
    private Performer performers;
    List<Performer> p = new ArrayList<>();
    List<Event> e = new ArrayList<>();
    private Optional optional;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        venue =new Venue(1610L,"Las Vegas","NV");
        performers =new Performer(605657L,"theater_family","Eiffel Tower Experience");
        p.add(performers);
        event =new Event(5851039L,"ramya","Eiffel Tower Experience - Las Vegas","family",venue,p);
        e.add(event);
        events = new Events(e);
        optional = Optional.of(event);
    }

    @AfterEach
    public void tearDown() {
        event = null;
    }

    @Test
    public void givenEventToSaveThenShouldReturnSavedEventToWishList(){
        when(eventRepository.save(any())).thenReturn(event);
        assertEquals(event, eventRepository.save(event));
        verify(eventRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllWishListedEventsThenShouldReturnListOfAllWishListedEvents() throws EventNotFoundException {
        String username="ramya";
        eventRepository.save(event);
        //stubbing the mock to return specific data
        when(eventRepository.findByUsername(username)).thenReturn((e));
        List<Event> eventList = wishlistService.getAllWishListedEvents(username);
        assertEquals(e,eventList);
        verify(eventRepository, times(1)).save(event);
        verify(eventRepository, times(2)).findByUsername(username);

    }

    @Test
    public void givenWishListIdToDeleteThenShouldNotReturnDeletedEvent() throws EventNotFoundException {
        when(eventRepository.findByIdAndUsername(event.getId(), event.getUsername())).thenReturn(Optional.of(event));
        String result = wishlistService.deleteWishListedEventById(5851039L,"ramya");
        verify(eventRepository, times(1)).findByIdAndUsername(event.getId(), event.getUsername());
    }
}
