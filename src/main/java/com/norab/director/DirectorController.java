package com.norab.director;

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

    @GetMapping("{actorId}/{movieId}")
    public Director getDirector(
        @PathVariable("actorId") Integer actorId,
        @PathVariable("movieId") Integer movieId) {
        return directorService.getDirector(actorId, movieId);
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

}
