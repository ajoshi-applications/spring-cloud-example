package com.example.controllers;

import com.example.domain.Reservation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationApiGatewayRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Source source;

    public Collection<String> getReservationNamesFallback() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void writeReservation(@RequestBody Reservation reservation) {
        Message<String> msg = MessageBuilder.withPayload(reservation.getReservationName()).build();
        this.source.output().send(msg);
    }

    @HystrixCommand(fallbackMethod = "getReservationNamesFallback")
    @RequestMapping(method = RequestMethod.GET, value = "/names")
    public Collection<String> getReservationNames() {
        ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() {
        };
        ResponseEntity<Resources<Reservation>> entity = this.restTemplate
                .exchange("http://localhost:8080/reservation-service/reservations", HttpMethod.GET, null, ptr);
        return entity.getBody()
                .getContent()
                .stream()
                .map(Reservation::getReservationName)
                .collect(Collectors.toList());
    }
}

