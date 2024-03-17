package com.cinema.administration.controller;

import com.cinema.administration.payload.CinemaHallPayload;
import com.cinema.administration.payload.LoginRequest;
import com.cinema.administration.payload.RegisterRequest;
import com.cinema.administration.payload.TokenPayload;
import com.cinema.administration.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/security")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping(path = "/register")
    public ResponseEntity<TokenPayload> registerCinemaHall(@RequestBody RegisterRequest registerRequest) {
        TokenPayload tokenPayload = securityService.registerCinemaHall(registerRequest);
        return new ResponseEntity<>(tokenPayload, HttpStatus.CREATED);
    }

    @PostMapping(path = "login")
    public ResponseEntity<TokenPayload> loginCinemaHall(@RequestBody LoginRequest loginRequest) {
        TokenPayload tokenPayload = securityService.loginCinemaHall(loginRequest);
        return new ResponseEntity<>(tokenPayload, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<CinemaHallPayload> getCinemaHall(Authentication authentication) {
        CinemaHallPayload cinemaHallPayload = securityService.getCinemaHall(authentication);
        return new ResponseEntity<>(cinemaHallPayload, HttpStatus.OK);
    }

}
