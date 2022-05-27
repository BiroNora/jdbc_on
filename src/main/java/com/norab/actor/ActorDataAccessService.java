package com.norab.actor;

import java.util.List;
import java.util.Optional;

public class ActorDataAccessService implements ActorDao {
    @Override
    public List<Actor> selectActor() {
        return null;
    }

    @Override
    public int insertActor(Actor actor) {
        return 0;
    }

    @Override
    public int deleteActor(int id) {
        return 0;
    }

    @Override
    public Optional<Actor> selectActorById(int id) {
        return Optional.empty();
    }

    @Override
    public int updateActor(int id, Actor actor) {
        return 0;
    }
}
