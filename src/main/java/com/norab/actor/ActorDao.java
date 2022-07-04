package com.norab.actor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Actor> selectActors();

    long insertActor(Actor actor) throws IllegalStateException;

    int deleteActor(Long id);

    Optional<Actor> selectActorById(Long id);

    int updateActor(Long id, Actor actor);
}
