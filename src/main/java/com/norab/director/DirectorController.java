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
    public List<Director> listDirectors() { return directorService.getDirectors(); }

    @GetMapping("{id}")
    public Director getDirector(@PathVariable("id") Integer actorId) { return directorService.getDirector(actorId); }

    @PostMapping
    public DirectorID addDirector(@RequestBody Director director) { return new DirectorID(directorService.insertDirector(director)); }

    @DeleteMapping("{id}")
    public void deleteDirector(@PathVariable("id") Integer actorId) { directorService.deleteDirector(actorId);}

    @PutMapping("{id}")
    public void updateDirector(
        @PathVariable("id") Integer actorId, @RequestBody Director director) {
        directorService.updateDirector(actorId, director);
    }
}
