package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.norab.crossed.SearchLocation.*;

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

    @GetMapping("/movies/{title}")
    public List<Movie> searchByMovieTitle(
        @PathVariable("title") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {

        return crossedService.searchByMovieTitle(title, location);
    }
    @GetMapping("/actors/date/{date}")
    public List<Actor> selectActorByBirthDate(@PathVariable("date") String date) {
        return crossedService.selectActorByBirthDate(date);
    }
}
