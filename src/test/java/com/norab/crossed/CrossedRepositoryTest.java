package com.norab.crossed;

import com.norab.movie.Movie;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrossedRepositoryTest {
    @Autowired
    private CrossedRepository repository;

    @Test
    @Order(1)
    void allMoviesByActor() {
        Long id = 1L;
        List<Movie> movies = repository.allMoviesByActor(id);
        assertEquals(movies.size(), 1);
        assertEquals(movies.get(0).getTitle(), "A Karib-tenger kalózai: A Fekete Gyöngy átka");
    }
}