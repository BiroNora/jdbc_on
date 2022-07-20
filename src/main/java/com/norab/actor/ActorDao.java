package com.norab.actor;

import com.norab.utils.DeleteResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Actor> selectActors();

    int insertActor(Actor actor) throws IllegalStateException;

    DeleteResult deleteActor(Integer actorId, boolean force);

    Optional<Actor> selectActorById(Integer actorId);
    List<Person> selectActorByName(String name, boolean match);

    int updateActor(Integer actorId, Actor actor);
}
