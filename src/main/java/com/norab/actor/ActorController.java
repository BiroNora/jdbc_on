package com.norab.actor;

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
    public Actor getActorId(@PathVariable("id") Long actorId) {
        return actorService.getActor(actorId);
    }

    @PostMapping
    public ActorId addActor(@RequestBody Actor actor) {
        return new ActorId(actorService.insertActor(actor));
    }

    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable("id") Long actorId) {
        actorService.deleteActor(actorId);
    }

    @PutMapping("{id}")
    public void updateActor(@PathVariable("id") Long actorId, @RequestBody(required = false) Actor actor) {
        actorService.updateActor(actorId, actor);
    }
}
