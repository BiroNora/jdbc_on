package com.norab.movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao<Movie> {
    List<Movie> selectMovies();
    int insertMovie(Movie movie);
    int deleteMovie(Long id);
    Optional<Movie> selectMovieById(Long id);
    int updateMovie(Long id, Movie movie);
}
