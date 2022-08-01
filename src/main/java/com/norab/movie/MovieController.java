package com.norab.movie;

import com.norab.utils.Page;
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
    public List<Movie> listMovies(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return movieService.listMovies(Page.of(page, size));
    }

    @GetMapping("{id}")
    public Movie getMovieId(@PathVariable("id") Integer movieId) {
        return movieService.getMovie(movieId);
    }

    @PostMapping
    public MovieID addMovie(@RequestBody Movie movie) {
        return new MovieID(movieService.insertMovie(movie));
    }

    @DeleteMapping("{id}")
    public void deleteMovie(
        @PathVariable("id") Integer movieId,
        @RequestParam(name = "force", required = false, defaultValue = "false") String forceParam) {
        boolean force = forceParam != null && forceParam.equalsIgnoreCase("true");
        movieService.deleteMovie(movieId, force);
    }

    @PutMapping("{id}")
    public void updateMovie(@PathVariable("id") Integer movieId, @RequestBody Movie movie) {
        movieService.updateMovie(movieId, movie);
    }
}
