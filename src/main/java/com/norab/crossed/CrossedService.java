package com.norab.crossed;

import com.norab.actor.Person;
import com.norab.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossedService {
    private final CrossedDao crossedDao;

    public CrossedService(CrossedDao crossedDao) {
        this.crossedDao = crossedDao;
    }

    public List<CrossedDao.MoviesByActor> allMoviesByActor(Integer id) {
        return crossedDao.allMoviesByActor(id);
    }

    public List<Movie> allMoviesByReleaseDateAsc() {
        return crossedDao.allMoviesByReleaseDateAsc();
    }

    public List<Movie> searchByMovieTitle(String title, SearchLocation location) {
        return crossedDao.searchByMovieTitle(title, location);
    }

    public List<Person> selectActorByBirthDate(String date) {
        return crossedDao.searchByActorBirthDate(date);
    }

    public List<ActorsByFilm> allActorsByFilm(String title) {
        return crossedDao.allActorsByFilm(title);
    }
}
