package com.norab.crossed;

import com.norab.actor.Person;
import com.norab.movie.Movie;
import com.norab.utils.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossedService {
    private final CrossedDao crossedDao;

    public CrossedService(CrossedDao crossedDao) {
        this.crossedDao = crossedDao;
    }

    public List<CrossedDao.MoviesByActor> allMoviesByActorById(Integer id) {

        return crossedDao.allMoviesByActorById(id);
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

    public List<ResultResponse> allPlaysByActor(String actorName) {
        return crossedDao.allPlaysByActor(actorName);
    }

    public List<CrossedDao.AllMoviesByActor> allMoviesByActorByName(String actorName) {
        return crossedDao.allMoviesByActorByName(actorName);
    }

    public List<CrossedDao.AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(String actorName) {
        return crossedDao.allMoviesAndPlaysByActor(actorName);
    }

    public List<CrossedDao.AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(String movieTitle, SearchLocation location) {
        return crossedDao.allPlaysAndActorsByMovie(movieTitle, location);
    }

    public List<ResultResponse> allPhotosByActor(String actorName) {
        return crossedDao.allPhotosByActor(actorName);
    }

    public List<ResultResponse> allPhotosByPlays(String roleName) {
        return crossedDao.allPhotosByPlays(roleName);
    }

    public List<ResultResponse> allPhotosByMovie(String movieTitle, SearchLocation location) {
        return crossedDao.allPhotosByMovie(movieTitle, location);
    }

    public List<CrossedDao.MovieSpecs> movieSpecification(String movieTitle, SearchLocation location) {
        return crossedDao.movieSpecification(movieTitle, location);
    }

    public List<CrossedDao.GenreActor> genresPerActor(String actorName) {
        return crossedDao.genresPerActor(actorName);
    }

    public List<CrossedDao.GenreActor> genresPerDirector(String actorName) {
        return crossedDao.genresPerDirector(actorName);
    }
}
