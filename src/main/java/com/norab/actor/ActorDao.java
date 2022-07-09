package com.norab.actor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Actor> selectActors();

    long insertActor(Actor actor) throws IllegalStateException;

    int deleteActor(Long actorId);

    Optional<Actor> selectActorById(Long actorId);

    int updateActor(Long actorId, Actor actor);
}
