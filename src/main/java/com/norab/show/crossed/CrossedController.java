package com.norab.show.crossed;

import com.norab.show.actor.Person;
import com.norab.show.movie.Movie;
import com.norab.utils.ResultResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/crossed")
public class CrossedController {
    private final CrossedService crossedService;

    public CrossedController(CrossedService crossedService) {
        this.crossedService = crossedService;
    }

    @GetMapping("/movies/actor/{id}")
    public List<CrossedDao.MoviesByActor> allMoviesByActor(@PathVariable("id") Integer id) {
        return crossedService.allMoviesByActorById(id);
    }

    @GetMapping("/movies/date")
    public List<Movie> allMoviesByReleaseDateAsc() {
        return crossedService.allMoviesByReleaseDateAsc();
    }

    @GetMapping("/movies")
    public List<Movie> searchByMovieTitle(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.searchByMovieTitle(title, location);
    }

    @GetMapping("/actors/date/{date}")
    public List<Person> searchByActorBirthDate(@PathVariable("date") Short date) {
        return crossedService.selectActorByBirthDate(date);
    }

    @GetMapping("/actorsbymovie")
    public List<ActorsByMovie> allActorsByMovie(
        @RequestParam(name = "title") String title) {
        return crossedService.allActorsByMovie(title);
    }

    @GetMapping("/playsbyactor")
    public List<ResultResponse> allPlaysByActor(
        @RequestParam(name = "name") String actorName) {
        return crossedService.allPlaysByActor(actorName);
    }

    @GetMapping("/moviesbyactor")
    public List<CrossedDao.AllMoviesByActor> allMoviesByActorByName(
        @RequestParam(name = "name") String actorName) {
        return crossedService.allMoviesByActorByName(actorName);
    }

    @GetMapping("/moviesplaysbyactor")
    public List<CrossedDao.AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(
        @RequestParam(name = "name") String actorName) {
        return crossedService.allMoviesAndPlaysByActor(actorName);
    }

    @GetMapping("/playsactorsbymovie")
    public List<CrossedDao.AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.allPlaysAndActorsByMovie(title, location);
    }

    @GetMapping("/photosbyactor")
    public List<ResultResponse> allPhotosByActor(
        @RequestParam(name = "name") String actorName) {
        return crossedService.allPhotosByActor(actorName);
    }

    @GetMapping("/photosbyrole")
    public List<ResultResponse> allPhotosByPlays(
        @RequestParam(name = "name") String roleName) {
        return crossedService.allPhotosByPlays(roleName);
    }

    @GetMapping("/photosbymovie")
    public List<ResultResponse> allPhotosByMovie(
        @RequestParam(name = "name") String roleName,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.allPhotosByMovie(roleName, location);
    }

    @GetMapping("/moviespec")
    public List<CrossedDao.MovieSpecs> movieSpecification(
        @RequestParam(name = "name") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.movieSpecification(title, location);
    }

    @GetMapping("/actgenres")
    public List<CrossedDao.GenreActor> genresPerActor(
        @RequestParam(name = "name") String actorName) {
        return crossedService.genresPerActor(actorName);
    }

    @GetMapping("/dirgenres")
    public List<CrossedDao.GenreActor> genresPerDirector(
        @RequestParam(name = "name") String actorName) {
        return crossedService.genresPerDirector(actorName);
    }
}
