package com.cinema.administration.controller;

import com.cinema.administration.payload.MovieRequest;
import com.cinema.administration.payload.CinemaHallSeatRequest;
import com.cinema.administration.service.CinemaHallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cinema-hall")
@RequiredArgsConstructor
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;

    @PostMapping(path = "/seat")
    public ResponseEntity<HttpStatusCode> addSeat(@RequestBody @Valid CinemaHallSeatRequest cinemaHallSeatRequest) {
        return cinemaHallService.addSeat(cinemaHallSeatRequest);
    }

    @PostMapping(path = "/movie")
    public ResponseEntity<HttpStatusCode> addMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return cinemaHallService.addMovie(movieRequest);
    }

}
