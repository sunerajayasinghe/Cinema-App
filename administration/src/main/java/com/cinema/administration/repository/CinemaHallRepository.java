package com.cinema.administration.repository;

import com.cinema.administration.domain.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {

    Optional<CinemaHall> findCinemaHallByEmailAddress(String emailAddress);

}
