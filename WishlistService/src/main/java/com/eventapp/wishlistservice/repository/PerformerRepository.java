package com.eventapp.wishlistservice.repository;

import com.eventapp.wishlistservice.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer,Long> {
}
