package com.norab.director;

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
    public List<Director> listDirectors() {
        return directorService.getDirectors();
    }

    @GetMapping("/exists")
    public ValidationResult getDirector(
        @RequestParam(name = "actor_id", required = true) Integer actorId,
        @RequestParam(name = "movie_id", required = true) Integer movieId) {
        return new ValidationResult(directorService.getDirector(actorId, movieId));
    }

    @PostMapping
    public DirectorID addDirector(@RequestBody Director director) {
        return new DirectorID(directorService.insertDirector(director));
    }

    @DeleteMapping("{actorId}/{movieId}")
    public void deleteDirector(
        @PathVariable("actorId") Integer actorId,
        @PathVariable("movieId") Integer movieId) {
        directorService.deleteDirector(actorId, movieId);
    }

    @GetMapping("/movies")
    public List<DirectorDao.MoviesByDirector> selectMoviesByDirector(
        @RequestParam(name = "name", required = true) String name) {
        return directorService.selectMoviesByDirector(name);
    }

}
