package com.example.configuration;

import com.example.domain.Reservation;
import com.example.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@Configuration
@ComponentScan("com.example")
public class DummyDataConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
        return strings -> {
            Stream.of("Josh", "Pieter", "Tasha", "Eric", "Susie", "Max")
                    .forEach(s -> reservationRepository.save(new Reservation(s)));
        };
    }
}
