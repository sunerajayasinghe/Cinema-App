package com.cinema.administration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cinema_hall")
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", unique = true, length = 320, nullable = false)
    private String emailAddress;

    @Column(name = "cinema_hall_name", nullable = false)
    private String cinemaHallName;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "number_of_seat_rows")
    private Integer numberSeatRows;

    @Column(name = "number_of_seat_columns")
    private Integer numberSeatColumns;

    @OneToMany(mappedBy = "cinemaHall", orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();

}
