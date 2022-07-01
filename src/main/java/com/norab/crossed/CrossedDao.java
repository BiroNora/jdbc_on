package com.norab.crossed;

import com.norab.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrossedDao {
    List<Movie> allMoviesByActor(Long id);
}
