package com.example.messaging;

import com.example.domain.Reservation;
import com.example.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@MessageEndpoint
public class ReservationProcesser {

    @Autowired
    private ReservationRepository reservationRepository;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void acceptNewReservation(String reservationName) {
        this.reservationRepository.save(new Reservation(reservationName));
    }
}
