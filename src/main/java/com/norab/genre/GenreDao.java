package com.norab.genre;

import com.norab.crossed.SearchLocation;

import java.util.List;

public interface GenreDao<Genre> {
    List<GenresByMovieId> selectGenres();

    List<String> selectAllGenre();

    boolean insertGenre(Genre genre);

    boolean deleteGenre(Integer movieId, String genre);

    //If relation exists between specified movie & genre
    boolean selectGenreById(Integer movieId, String genre);

    List<String> selectGenresByMovieId(Integer movieId);

    //CROSSED one genre and related films
    List<MoviesByGenre> selectMoviesByGenre(String genre);

    //Genres of one film
    List<GenresByMovie> selectGenresByMovieTitle(String title, SearchLocation location);

    record GenresByMovieId(
        Integer movieId,
        List<String> genres
    ) {
    }

    record MoviesByGenre(
        String title,
        String titleOriginal,
        Short releaseDate,
        String genre
    ) {
    }

    record GenresByMovie(
        String title,
        String titleOriginal,
        Short releaseDate,
        List<String> genres
    ) {
    }
}
