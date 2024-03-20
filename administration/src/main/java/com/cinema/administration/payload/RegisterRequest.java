package com.cinema.administration.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank @Email String emailAddress, @NotBlank String password,
                              @NotBlank String mobileNumber, @NotBlank String cinemaHallName) {

}
