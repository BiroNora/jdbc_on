package com.norab.crossed;

import com.norab.actor.Person;
import com.norab.movie.Movie;
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
        return crossedService.allMoviesByActor(id);
    }

    @GetMapping("/movies/date")
    public List<Movie> allMoviesByReleaseDateAsc() {
        return crossedService.allMoviesByReleaseDateAsc();
    }

    @GetMapping("/movies")
    public List<Movie> searchByMovieTitle(
        @RequestParam(name = "q", required = true) String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.searchByMovieTitle(title, location);
    }

    @GetMapping("/actors/date/{date}")
    public List<Person> searchByActorBirthDate(@PathVariable("date") Short date) {
        return crossedService.selectActorByBirthDate(date);
    }

    @GetMapping("/actorsbymovie")
    public List<ActorsByMovie> allActorsByMovie(
        @RequestParam(name = "q", required = true) String title) {
        return crossedService.allActorsByMovie(title);
    }

    @GetMapping("/playsbyactor")
    public List<String> allPlaysByActor(
        @RequestParam(name = "name", required = true) String actorName) {
        return crossedService.allPlaysByActor(actorName);
    }

    @GetMapping("/moviesbyactor")
    public List<CrossedDao.AllMoviesByActor> allMoviesByActor(
        @RequestParam(name = "name", required = true) String actorName) {
        return crossedService.allMoviesByActor(actorName);
    }

    @GetMapping("/moviesplaysbyactor")
    public List<CrossedDao.AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(
        @RequestParam(name = "name", required = true) String actorName) {
        return crossedService.allMoviesAndPlaysByActor(actorName);
    }

    @GetMapping("/playsactorsbymovie")
    public List<CrossedDao.AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(
        @RequestParam(name = "name", required = true) String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.allPlaysAndActorsByMovie(title, location);
    }

    @GetMapping("/photosbyactor")
    public List<String> allPhotosByActor(
        @RequestParam(name = "name", required = true) String actorName) {
        return crossedService.allPhotosByActor(actorName);
    }

    @GetMapping("/photosbyrole")
    public List<String> allPhotosByPlays(
        @RequestParam(name = "name", required = true) String roleName) {
        return crossedService.allPhotosByPlays(roleName);
    }

    @GetMapping("/photosbymovie")
    public List<String> allPhotosByMovie(
        @RequestParam(name = "name", required = true) String roleName,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.allPhotosByMovie(roleName, location);
    }

    @GetMapping("/specsbymovie")
    public List<CrossedDao.MovieSpecs> movieSpecification(
        @RequestParam(name = "name", required = true) String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return crossedService.movieSpecification(title, location);
    }
}
