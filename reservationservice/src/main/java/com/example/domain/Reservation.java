package com.example.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ajoshi on 24-Dec-16.
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString
public class Reservation {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String reservationName;
}
