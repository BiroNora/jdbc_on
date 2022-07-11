package com.norab.director;

import java.util.List;
import java.util.Optional;

public interface DirectorDao<Director> {
    List<Director> selectDirectors();

    int insertDirector(Director director);

    boolean deleteDirector(Integer actorId);

    Optional<Director> selectDirectorById(Integer actorId);

    boolean updateDirector(Integer actorId, Director director);
}
