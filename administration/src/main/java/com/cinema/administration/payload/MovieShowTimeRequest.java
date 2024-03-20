package com.cinema.administration.payload;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovieShowTimeRequest(@NotNull LocalDateTime showStartTime, @NotNull LocalDateTime showEndTime) {

}
