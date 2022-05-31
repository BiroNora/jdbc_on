package com.norab.actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao<Actor> {
    List<Actor> selectActors();
    int insertActor(Actor actor);

    int deleteActor(int id);
    Optional<Actor> selectActorById(int id);
    int updateActor(int id, Actor actor);
}
