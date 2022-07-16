package com.norab.movie;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieDao<Movie> {
    List<Movie> selectMovies();

    int insertMovie(Movie movie) throws IllegalStateException;

    int deleteMovie(Integer movieId);

    Optional<Movie> selectMovieById(Integer movieId);

    int updateMovie(Integer movieId, Movie movie);
}
