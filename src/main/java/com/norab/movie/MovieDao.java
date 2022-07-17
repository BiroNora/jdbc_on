package com.norab.movie;

import com.norab.utils.DeleteResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieDao<Movie> {
    List<Movie> selectMovies();

    int insertMovie(Movie movie) throws IllegalStateException;

    DeleteResult deleteMovie(Integer movieId, boolean force);

    Optional<Movie> selectMovieById(Integer movieId);

    int updateMovie(Integer movieId, Movie movie);
}
