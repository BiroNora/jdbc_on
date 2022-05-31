package com.norab.movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao<Movie> {
    List<Movie> selectMovies();
    int insertMovie(Movie movie);
    int deleteMovie(int id);
    Optional<Movie> selectMovieById(int id);
    int updateMovie(int id, Movie movie);
}
