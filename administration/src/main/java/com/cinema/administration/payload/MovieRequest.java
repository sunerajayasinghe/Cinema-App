package com.cinema.administration.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovieRequest(@NotNull Long cinemaHallId, @NotBlank String movieName,
                           @NotEmpty List<MovieShowTimeRequest> showTimes) {

}
