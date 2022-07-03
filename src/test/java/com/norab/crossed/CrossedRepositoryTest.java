package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class CrossedRepositoryTest {
    @Autowired
    private CrossedRepository repository;

    @Test
    void allMoviesByActor() {
        Long id = 1L;
        List<Movie> movies = repository.allMoviesByActor(id);
        assertEquals(movies.size(), 1);
        assertEquals(movies.get(0).getTitle(), "A Karib-tenger kalózai: A Fekete Gyöngy átka");
    }

    @Test
    void allMoviesByReleaseDateAsc() {
        List<Movie> movies = repository.allMoviesByReleaseDateAsc();
        for (Movie m : movies) {
            System.out.println(m);
        }
        assertEquals(movies.size(), 9);
    }

    @Test
    void selectActorByBirthDate() {
        String date = "1963%";
        List<Actor> actors = repository.selectActorByBirthDate(date);
        for (Actor a : actors) {
            System.out.println(a);
        }
        assertTrue(actors.size() > 0);
    }

}