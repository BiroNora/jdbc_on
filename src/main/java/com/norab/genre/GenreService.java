package com.norab.genre;


import com.norab.crossed.SearchLocation;
import com.norab.exception.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    boolean insertGenre(Genre genre) {
        Integer movieId = genre.getMovieId();
        System.out.println(movieId);
        List<Genre> genres = genreDao.selectGenres();
        for (Genre g : genres) {
            if (Objects.equals(g.getMovieId(), movieId))
            System.out.println(g);
        }
        List<Genre> collect = genres.stream()
            .filter(x -> Objects.equals(x.getMovieId(), movieId)).toList();
        System.out.println(collect.size());
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
