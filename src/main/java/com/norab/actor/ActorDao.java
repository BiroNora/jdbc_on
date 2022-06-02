package com.norab.actor;

import com.norab.movie.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ActorDao<Actor> {
    List<Movie> allMoviesByActor(Integer id);
    List<Actor> selectActors();
    int insertActor(Actor actor);
    int deleteActor(int id);
    Optional<Actor> selectActorById(int id);
    int updateActor(int id, Actor actor);
}
