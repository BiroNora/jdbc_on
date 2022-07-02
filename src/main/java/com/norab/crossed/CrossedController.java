package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/movies")
    public List<Movie> allMoviesByReleaseDateAsc() {
        return crossedService.allMoviesByReleaseDateAsc();
    }
    @GetMapping("/actors/date/{date}")
    public List<Actor> selectActorByBirthDate(@PathVariable("date") String date) {
        return crossedService.selectActorByBirthDate(date);
    }
}