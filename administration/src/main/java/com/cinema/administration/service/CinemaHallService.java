package com.cinema.administration.service;

import com.cinema.administration.domain.CinemaHall;
import com.cinema.administration.domain.Movie;
import com.cinema.administration.domain.ShowTime;
import com.cinema.administration.payload.CinemaHallSeatRequest;
import com.cinema.administration.payload.MovieRequest;
import com.cinema.administration.repository.CinemaHallRepository;
import com.cinema.administration.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;
    private final MovieRepository movieRepository;

    public ResponseEntity<HttpStatusCode> addSeat(CinemaHallSeatRequest cinemaHallSeatRequest) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallSeatRequest.cinemaHallId()).orElseThrow();

        cinemaHall.setNumberSeatRows(cinemaHallSeatRequest.rowCount());
        cinemaHall.setNumberSeatColumns(cinemaHallSeatRequest.columnCount());

        cinemaHallRepository.save(cinemaHall);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<HttpStatusCode> addMovie(MovieRequest movieRequest) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(movieRequest.cinemaHallId()).orElseThrow();

        Movie movie = new Movie();

        List<ShowTime> showTimes = movieRequest.showTimes().stream()
                .map(showTimeRequest -> {
                    ShowTime showTime = new ShowTime();
                    showTime.setShowStartTime(showTimeRequest.showStartTime());
                    showTime.setShowEndTime(showTimeRequest.showEndTime());
                    showTime.setMovie(movie);
                    return showTime;
                }).toList();

        movie.setCinemaHall(cinemaHall);
        movie.setMovieName(movieRequest.movieName());
        movie.setShowTimes(showTimes);

        movieRepository.save(movie);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
