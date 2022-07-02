package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossedService {
    private final CrossedDao crossedDao;

    public CrossedService(CrossedDao crossedDao) {
        this.crossedDao = crossedDao;
    }

    public List<Movie> allMoviesByActor(Long id) {
        return crossedDao.allMoviesByActor(id);
    }

    public List<Actor> selectActorByBirthDate(String date) {
        return crossedDao.selectActorByBirthDate(date);
    }
}
