package com.cinema.administration.service;

import com.cinema.administration.payload.CinemaHallPayload;
import com.cinema.administration.payload.LoginRequest;
import com.cinema.administration.payload.RegisterRequest;
import com.cinema.administration.payload.TokenPayload;
import com.cinema.administration.domain.CinemaHall;
import com.cinema.administration.repository.CinemaHallRepository;
import com.cinema.administration.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final CinemaHallRepository cinemaHallRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public TokenPayload registerCinemaHall(RegisterRequest registerRequest) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findCinemaHallByEmailAddress(registerRequest.emailAddress());
        if (cinemaHallOptional.isPresent()) {
            return null;
        }

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setEmailAddress(registerRequest.emailAddress());
        cinemaHall.setPassword(passwordEncoder.encode(registerRequest.password()));
        cinemaHall.setCinemaHallName(registerRequest.cinemaHallName());
        cinemaHall.setMobileNumber(registerRequest.mobileNumber());
        cinemaHallRepository.save(cinemaHall);

        return getTokenResponseResponseData(registerRequest.emailAddress(), registerRequest.password());
    }

    public TokenPayload loginCinemaHall(LoginRequest loginRequest) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findCinemaHallByEmailAddress(loginRequest.emailAddress());
        if (cinemaHallOptional.isEmpty()) {
            return null;
        }

        return getTokenResponseResponseData(loginRequest.emailAddress(), loginRequest.password());
    }

    public CinemaHallPayload getCinemaHall(Authentication authentication) {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findCinemaHallByEmailAddress(authentication.getName());
        if (cinemaHallOptional.isEmpty()) {
            return null;
        }

        CinemaHall cinemaHall = cinemaHallOptional.get();
        return new CinemaHallPayload(cinemaHall.getEmailAddress(), cinemaHall.getMobileNumber(), cinemaHall.getCinemaHallName());
    }

    private TokenPayload getTokenResponseResponseData(String emailAddress, String password) {
        Authentication authentication = setAuthentication(emailAddress, password);

        String jwtToken = jwtUtil.generateJwtToken(authentication);

        return new TokenPayload(jwtToken);
    }

    private Authentication setAuthentication(String emailAddress, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(emailAddress, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

}
