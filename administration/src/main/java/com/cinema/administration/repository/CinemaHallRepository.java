package com.cinema.administration.repository;

import com.cinema.administration.domain.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {

    Optional<CinemaHall> findCinemaHallByEmailAddress(String emailAddress);

}
