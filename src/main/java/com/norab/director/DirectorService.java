package com.norab.director;

import com.norab.exception.AlreadyExistsException;
import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorDao directorDao;

    public DirectorService(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    public List<Director> getDirectors() {
        return directorDao.selectDirectors();
    }

    public int insertDirector(Director director) {
        Integer movieId = director.getMovieId();
        List<Director> directors = directorDao.selectDirectors();
        List<Director> collect = directors.stream()
            .filter(x -> x.getMovieId() == movieId).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This id already exists");
        }
        return directorDao.insertDirector(director);
    }

    public void deleteDirector(Integer actorId) {
        Optional<Director> director1 = directorDao.selectDirectorById(actorId);
        director1.ifPresentOrElse(director -> {
            boolean result = directorDao.deleteDirector(actorId);
            if (!result) {
                throw new IllegalStateException("oops could not delete director");
            }
        }, () -> {
            throw new NotFoundException(String.format("Director with id %s not found", actorId));
        });
    }

    public Director getDirector(Integer actorId) {
        try {
            return (Director) directorDao.selectDirectorById(actorId)
                .orElseThrow(() -> new NotFoundException(String.format("Director with id %s not found", actorId)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDirector(Integer actorId, Director director) {
        if (directorDao.selectDirectorById(actorId).isPresent()) {
            Director director1 = new Director(actorId, director.getMovieId());
            directorDao.updateDirector(actorId, director1);
        } else {
            throw new NotFoundException(String.format("Director with id %s not found", actorId));
        }
    }
}
