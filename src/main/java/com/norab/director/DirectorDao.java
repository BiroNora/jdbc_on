package com.norab.director;

import com.norab.movie.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DirectorDao<Director> {
    List<Director> selectDirectors();

    int insertDirector(Director director);

    boolean deleteDirector(Integer actorId, Integer movieId);

    //if relation exists between specified director & movie
    Optional<Director> selectDirectorById(Integer actorId, Integer movieId);

    //CROSSED one director and related films
    List<MoviesByDirector> selectMoviesByDirector(String name);
    record MoviesByDirector(
        String title,
        String titleOriginal,
        LocalDate releaseDate
    ) {
    }

    //films of one director
    Optional<Director> selectDirectorsByMovieId(Integer movieId);
}
