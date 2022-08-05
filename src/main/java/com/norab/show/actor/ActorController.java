package com.norab.show.actor;

import com.norab.utils.Page;
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
    public List<Person> listActors(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return actorService.listActors(Page.of(page, size));
    }

    @GetMapping("{id}")
    public Person getActorId(@PathVariable("id") Integer actorId) {
        return actorService.getActor(actorId);
    }

    @GetMapping("/find")
    public List<Person> selectActorByName(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "match", required = false, defaultValue = "false") String match) {
        boolean m = match != null && match.equalsIgnoreCase("true");
        return actorService.selectActorByName(name, m);
    }

    @PostMapping
    public ActorID addActor(@RequestBody Person actor) {
        return new ActorID(actorService.insertActor(actor));
    }

    @DeleteMapping("{id}")
    public void deleteActor(
        @PathVariable("id") Integer actorId,
        @RequestParam(name = "force", required = false, defaultValue = "false") String forceParam) {
        boolean force = forceParam != null && forceParam.equalsIgnoreCase("true");
        actorService.deleteActor(actorId, force);
    }

    @PutMapping("{id}")
    public void updateActor(@PathVariable("id") Integer actorId, @RequestBody(required = false) Person actor) {
        actorService.updateActor(actorId, actor);
    }
}
