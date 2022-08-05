package com.norab.show.movie;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieDao<Movie> {
    List<Movie> listMovies(Page page);

    int insertMovie(Movie movie) throws IllegalStateException;

    DeleteResult deleteMovie(Integer movieId, boolean force);

    Optional<Movie> selectMovieById(Integer movieId);

    int updateMovie(Integer movieId, Movie movie);
}
