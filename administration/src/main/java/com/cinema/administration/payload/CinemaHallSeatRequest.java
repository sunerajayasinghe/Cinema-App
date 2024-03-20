package com.cinema.administration.payload;

import jakarta.validation.constraints.NotNull;

public record CinemaHallSeatRequest(@NotNull Long cinemaHallId, @NotNull Integer rowCount, @NotNull Integer columnCount) {

}
