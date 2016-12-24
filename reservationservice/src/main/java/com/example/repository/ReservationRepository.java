package com.example.repository;

import com.example.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @RestResource(path = "by-name")
    Collection<Reservation> findByReservationName(String reservationName);
}
