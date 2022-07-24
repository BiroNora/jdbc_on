package com.norab.director;

import com.norab.crossed.SearchLocation;
import com.norab.exception.AlreadyExistsException;
import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorDao directorDao;

    public DirectorService(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    public List<Director> getDirectors() {
        return directorDao.selectDirectors();
    }

    public boolean insertDirector(Director director) {
        Integer movieId = director.getMovieId();
        List<Director> directors = directorDao.selectDirectors();
        List<Director> collect = directors.stream()
            .filter(x -> x.getMovieId() == movieId).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This id already exists");
        }
        return directorDao.insertDirector(director);
    }

    public void deleteDirector(Integer actorId, Integer movieId) {
        boolean result = directorDao.deleteDirector(actorId, movieId);
        if (!result) {
            throw new IllegalStateException("oops could not delete director");
        }
    }

    public boolean getDirector(Integer actorId, Integer movieId) {
        return directorDao.selectDirectorById(actorId, movieId);
    }

    public List<DirectorDao.MoviesByDirector> selectMoviesByDirector(String name) {
        return directorDao.selectMoviesByDirector(name);
    }

    public List<String> selectDirectorsByMovieTitle(String title, SearchLocation location) {
        return directorDao.selectDirectorsByMovieTitle(title, location);
    }
}
