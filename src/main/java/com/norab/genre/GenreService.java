package com.norab.genre;


import com.norab.crossed.SearchLocation;
import com.norab.exception.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public List<Genre> selectGenres() {
        return genreDao.selectGenres();
    }

    List<String> selectAllGenre() {
        return genreDao.selectAllGenre();
    }

    int insertGenre(Genre genre) {
        Integer movieId = genre.getMovieId();
        List<Genre> genres = genreDao.selectGenres();
        List<Genre> collect = genres.stream()
            .filter(x -> x.getMovieId() == movieId).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This id already exists");
        }
        return genreDao.insertGenre(genre);
    }

    boolean deleteGenre(Integer movieId, String genre) {
        return genreDao.deleteGenre(movieId, genre);
    }

    //If relation exists between specified movie & genre
    boolean selectGenreById(Integer movieId, String genre) {
        return genreDao.selectGenreById(movieId, genre);
    }

    public List<String> selectGenresByMovieId(Integer movieId) {
        return genreDao.selectGenresByMovieId(movieId);
    }

    //CROSSED one genre and related films
    List<GenreDao.MoviesByGenre> selectMoviesByGenre(String genre) {
        return genreDao.selectMoviesByGenre(genre);
    }

    //Genres of one film
    List<GenreDao.MoviesByGenre> selectGenresByMovieTitle(String title, SearchLocation location) {
        return genreDao.selectGenresByMovieTitle(title, location);
    }
}
