package com.norab.crossed;

import com.norab.actor.Actor;
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

    @GetMapping("{id}/movies")
    public List<Movie> allMoviesByActor(@PathVariable("id") Long id) {
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
    public List<Actor> searchByActorBirthDate(@PathVariable("date") String date) {
        return crossedService.selectActorByBirthDate(date);
    }

    @GetMapping("/actorsbyfilm")
    public List<ActorsByFilm> allActorsByFilm(
        @RequestParam(name = "q", required = true) String title) {
        return crossedService.allActorsByFilm(title);
    }
}
