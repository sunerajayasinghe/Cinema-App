package com.cinema.administration.security;

import com.cinema.administration.domain.CinemaHall;
import com.cinema.administration.repository.CinemaHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final CinemaHallRepository cinemaHallRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findCinemaHallByEmailAddress(username);
        if (cinemaHallOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with the email address: " + username);
        }

        CinemaHall cinemaHall = cinemaHallOptional.get();
        return new User(cinemaHall.getEmailAddress(), cinemaHall.getPassword(), new ArrayList<>());
    }

}
