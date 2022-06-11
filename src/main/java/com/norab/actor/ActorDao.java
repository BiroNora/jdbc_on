package com.norab.actor;

import com.norab.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Movie> allMoviesByActor(Long id);

    List<Actor> selectActors();

    int insertActor(Actor actor);

    int deleteActor(Long id);

    Optional<Actor> selectActorById(Long id);

    int updateActor(Long id, Actor actor);
}
