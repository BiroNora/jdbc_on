package com.norab.director;

import com.norab.crossed.SearchLocation;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;

import java.util.List;

public interface DirectorDao<Director> {
    List<ResultResponse> listDirectors(Page page);

    List<Directors> listDirectorsAndMovies();

    boolean insertDirector(Director director);

    boolean deleteDirector(Integer actorId, Integer movieId);

    //If relation exists between specified director & movie
    boolean selectDirectorById(Integer actorId, Integer movieId);

    //CROSSED one director and related films
    List<MoviesByDirector> selectMoviesByDirector(String name);

    //Films of one director
    List<ResultResponse> selectDirectorsByMovieTitle(String title, SearchLocation location);

    record MoviesByDirector(
        String title,
        String titleOriginal,
        Short releaseDate,
        String fullName
    ) {
    }

    record Directors(
        List<String> fullName,
        String title,
        String titleOriginal,
        Short releaseDate
    ) {
    }
}
