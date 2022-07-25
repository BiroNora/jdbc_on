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

    public List<Person> selectActorByBirthDate(Short date) {
        return crossedDao.searchByActorBirthDate(date);
    }

    public List<ActorsByMovie> allActorsByMovie(String title) {
        return crossedDao.allActorsByMovie(title);
    }

    public List<String> allPlaysByActor(String actorName) {
        return crossedDao.allPlaysByActor(actorName);
    }

    public List<CrossedDao.AllMoviesByActor> allMoviesByActor(String actorName) {
        return crossedDao.allMoviesByActor(actorName);
    }

    public List<CrossedDao.AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(String actorName) {
        return crossedDao.allMoviesAndPlaysByActor(actorName);
    }

    public List<CrossedDao.AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(String movieTitle, SearchLocation location) {
        return crossedDao.allPlaysAndActorsByMovie(movieTitle, location);
    }

    public List<String> allPhotosByActor(String actorName) {
        return crossedDao.allPhotosByActor(actorName);
    }

    public List<String> allPhotosByPlays(String roleName) {
        return crossedDao.allPhotosByPlays(roleName);
    }

    public List<String> allPhotosByMovie(String movieTitle, SearchLocation location) {
        return crossedDao.allPhotosByMovie(movieTitle, location);
    }

    public List<CrossedDao.MovieSpecs> movieSpecification(String movieTitle, SearchLocation location) {
        return crossedDao.movieSpecification(movieTitle, location);
    }
}
