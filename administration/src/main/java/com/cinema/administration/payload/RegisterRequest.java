package com.cinema.administration.payload;

public record RegisterRequest(String emailAddress, String password, String mobileNumber, String cinemaHallName) {

}
