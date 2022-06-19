package com.norab.movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> listMovies() {
        return movieService.getMovies();
    }

    @GetMapping("{id}")
    public Movie getMovieId(@PathVariable("id") Long id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    public MovieId addMovie(@RequestBody Movie movie) {
        return new MovieId(movieService.insertMovie(movie));
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
    }

    @PutMapping("{id}")
    public void updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        movieService.updateMovie(id, movie);
    }
}
