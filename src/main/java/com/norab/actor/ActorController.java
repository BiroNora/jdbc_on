package com.norab.actor;

import com.norab.movie.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {

        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> listActors() {
        return actorService.getActors();
    }

    @GetMapping("{id}")
    public Actor getActorId(@PathVariable("id") Long id) {
        return actorService.getActor(id);
    }

    @PostMapping
    public ActorId addActor(@RequestBody Actor actor) {
        return new ActorId(actorService.insertActor(actor));
    }

    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable("id") Long id) {
        actorService.deleteActor(id);
    }

    @PutMapping("{id}")
    public void updateActor(@PathVariable("id") Long id, @RequestBody(required = false) Actor actor) {
        actorService.updateActor(id, actor);
    }
}
