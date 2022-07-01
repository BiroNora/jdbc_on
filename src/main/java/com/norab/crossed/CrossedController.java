package com.norab.crossed;

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
}
