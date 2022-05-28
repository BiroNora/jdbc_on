package com.norab.actor;

import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getActors() {
        return actorDao.selectActors();
    }

    public void addNewActor(Actor actor) {
        String actorName = actor.fullName();
        List<Actor> actors = actorDao.selectActors();
        List<Actor> collect = actors.stream()
            .filter(x -> x.fullName().equals(actorName)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this artist already exists");
        }
        int result = actorDao.insertActor(actor);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }

    }

    public void deleteActor(Integer id) {
        Optional<Actor> actor1 = actorDao.selectActorById(id);
        actor1.ifPresentOrElse(actor -> {
            int result = actorDao.deleteActor(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete actor");
            }
        }, () -> {
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        });
    }

    public Actor getActor(int id) {
        return actorDao.selectActorById(id).orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", id)));
    }

    public void updateActor(int id, Actor actor) {
        if (actorDao.selectActorById(id).isPresent()) {
            Actor actor1 = new Actor(id, actor.fullName(), actor.birthDate());
            actorDao.updateActor(id, actor1);
        } else {
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        }
    }
}
