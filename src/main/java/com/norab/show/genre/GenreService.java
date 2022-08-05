package com.norab.show.genre;


import com.norab.show.crossed.SearchLocation;
import com.norab.utils.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public List<GenreDao.GenresByMovieId> selectGenres() {
        return genreDao.selectGenres();
    }

    List<ResultResponse> selectAllGenre() {
        return genreDao.selectAllGenre();
    }

    boolean insertGenre(Genre genre) {
        return genreDao.insertGenre(genre);
    }

    boolean deleteGenre(Integer movieId, String genre) {
        return genreDao.deleteGenre(movieId, genre);
    }

    //If relation exists between specified movie & genre
    boolean selectGenreById(Integer movieId, String genre) {
        return genreDao.selectGenreById(movieId, genre);
    }

    public List<ResultResponse> selectGenresByMovieId(Integer movieId) {
        return genreDao.selectGenresByMovieId(movieId);
    }

    //CROSSED one genre and related films
    List<GenreDao.MoviesByGenre> selectMoviesByGenre(String genre) {
        return genreDao.selectMoviesByGenre(genre);
    }

    //Genres of one film
    List<GenreDao.GenresByMovie> selectGenresByMovieTitle(String title, SearchLocation location) {
        return genreDao.selectGenresByMovieTitle(title, location);
    }
}
