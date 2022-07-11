package com.norab.director;

import java.util.List;
import java.util.Optional;

public interface DirectorDao<Director> {
    List<Director> selectDirectors();

    int insertDirector(Director director);

    boolean deleteDirector(Integer actorId, Integer movieId);

    //if relation exists between specified director & movie
    Optional<Director> selectDirectorById(Integer actorId, Integer movieId);
}
