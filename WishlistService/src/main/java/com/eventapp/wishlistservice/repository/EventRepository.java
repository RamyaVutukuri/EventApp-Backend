package com.eventapp.wishlistservice.repository;

import com.eventapp.wishlistservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    Optional<Event> findByIdAndUsername(Long id,String Username);

    List<Event> findByUsername(String Username);

}
