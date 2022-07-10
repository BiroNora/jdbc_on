package com.norab.actor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Actor> selectActors();

    int insertActor(Actor actor) throws IllegalStateException;

    int deleteActor(Integer actorId);

    Optional<Actor> selectActorById(Integer actorId);

    int updateActor(Integer actorId, Actor actor);
}
