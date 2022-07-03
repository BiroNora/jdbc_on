package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossedService {
    private final CrossedDao crossedDao;

    public CrossedService(CrossedDao crossedDao) {
        this.crossedDao = crossedDao;
    }

    public List<Movie> allMoviesByActor(Long id) {
        return crossedDao.allMoviesByActor(id);
    }

    public List<Movie> allMoviesByReleaseDateAsc() {
        return crossedDao.allMoviesByReleaseDateAsc();
    }

    public List<Movie> searchByMovieTitle(String title, SearchLocation location) {
        return crossedDao.searchByMovieTitle(title, location);
    }

    public List<Actor> selectActorByBirthDate(String date) {
        return crossedDao.searchByActorBirthDate(date);
    }

    public List<Actor> allActorsByFilm(String title, SearchLocation location) {
        return crossedDao.allActorsByFilm(title, location);
    }
}
