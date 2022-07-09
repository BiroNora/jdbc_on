package com.norab.movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao<Movie> {
    List<Movie> selectMovies();

    long insertMovie(Movie movie) throws IllegalStateException;

    int deleteMovie(Long movieId);

    Optional<Movie> selectMovieById(Long movieId);

    int updateMovie(Long movieId, Movie movie);
}
