package com.norab.director;

import com.norab.crossed.SearchLocation;
import com.norab.exception.NotFoundException;
import com.norab.utils.BooleanResponse;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;
import com.norab.utils.ValidationResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<ResultResponse> listDirectors(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return directorService.listDirectors(Page.of(page, size));
    }

    @GetMapping("/all")
    public List<DirectorDao.Directors> listDirectorsAndMovies() {
        return directorService.listDirectorsAndMovies();
    }


    @GetMapping("/exists")
    public ValidationResult getDirector(
        @RequestParam(name = "actorid") Integer actorId,
        @RequestParam(name = "movieid") Integer movieId) {
        return new ValidationResult(directorService.getDirector(actorId, movieId));
    }

    @GetMapping("/movies")
    public List<DirectorDao.MoviesByDirector> selectMoviesByDirector(
        @RequestParam(name = "name") String name) {
        return directorService.selectMoviesByDirector(name);
    }

    @GetMapping("/dirbymovie")
    public List<ResultResponse> selectDirectorsByMovieTitle(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return directorService.selectDirectorsByMovieTitle(title, location);
    }

    @PostMapping
    public BooleanResponse insertDirector(@RequestBody Director director) {
        return new BooleanResponse(directorService.insertDirector(director));
    }

    @DeleteMapping("{actorid}/{movieid}")
    public void deleteDirector(
        @PathVariable("actorid") Integer actorId,
        @PathVariable("movieid") Integer movieId) {
        if (!directorService.deleteDirector(actorId, movieId)) {
            throw new NotFoundException("Director is not found");
        }
    }
}
