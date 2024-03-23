package com.cinema.administration.service;

import com.cinema.administration.domain.Movie;
import com.cinema.administration.repository.MovieRepository;
import com.example.administration.proto.CinemaHallPayload;
import com.example.administration.proto.MovieDetailsGrpc;
import com.example.administration.proto.MovieList;
import com.example.administration.proto.MoviePayload;
import com.example.administration.proto.ShowTime;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class MovieDetailsImplementation extends MovieDetailsGrpc.MovieDetailsImplBase {

    private final MovieRepository movieRepository;

    @Override
    public void sendMovieDetails(Empty request, StreamObserver<MovieList> responseObserver) {
        List<Movie> movies = movieRepository.findAll();

        MovieList.Builder movieListBuilder = MovieList.newBuilder();

        for (Movie movie : movies) {
            MoviePayload.Builder moviePayloadBuilder = MoviePayload.newBuilder()
                    .setId(movie.getId())
                    .setMovieName(movie.getMovieName());

            CinemaHallPayload cinemaHallPayload = CinemaHallPayload.newBuilder()
                    .setId(movie.getCinemaHall().getId())
                    .setEmailAddress(movie.getCinemaHall().getEmailAddress())
                    .setMobileNumber(movie.getCinemaHall().getMobileNumber())
                    .setCinemaHallName(movie.getCinemaHall().getCinemaHallName())
                    .build();
            moviePayloadBuilder.setCinemaHall(cinemaHallPayload);

            for (com.cinema.administration.domain.ShowTime showTimeDetail : movie.getShowTimes()) {
                Instant startInstant = showTimeDetail.getShowStartTime().atZone(ZoneId.systemDefault()).toInstant();
                Timestamp startTimestamp = Timestamp.newBuilder()
                        .setSeconds(startInstant.getEpochSecond())
                        .setNanos(startInstant.getNano())
                        .build();

                Instant endInstant = showTimeDetail.getShowEndTime().atZone(ZoneId.systemDefault()).toInstant();
                Timestamp endTimestamp = Timestamp.newBuilder()
                        .setSeconds(endInstant.getEpochSecond())
                        .setNanos(endInstant.getNano())
                        .build();

                ShowTime showTime = ShowTime.newBuilder()
                        .setId(showTimeDetail.getId())
                        .setShowStartTime(startTimestamp)
                        .setShowEndTime(endTimestamp)
                        .build();
                moviePayloadBuilder.addShowTimes(showTime);
            }

            movieListBuilder.addMoviePayload(moviePayloadBuilder.build());
        }

        MovieList movieList = movieListBuilder.build();
        responseObserver.onNext(movieList);
        responseObserver.onCompleted();
    }

}
