package com.cinema.administration.payload;

import com.cinema.administration.domain.ShowTime;

import java.util.List;

public record MoviePayload(Long id, String movieName, CinemaHallPayload cinemaHall, List<ShowTime> showTimes) {

}
