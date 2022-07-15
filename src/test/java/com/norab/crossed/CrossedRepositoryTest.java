package com.norab.crossed;

import com.norab.actor.Person;
import com.norab.movie.Movie;
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
        Integer id = 1;
        List<CrossedDao.MoviesByActor> movies = repository.allMoviesByActor(id);
        assertEquals(2, movies.size());
        assertEquals("A Karib-tenger kalózai: A Fekete Gyöngy átka", movies.get(0).title());
    }

    @Test
    void allMoviesByReleaseDateAsc() {
        List<Movie> movies = repository.allMoviesByReleaseDateAsc();
        for (Movie m : movies) {
            System.out.println(m);
        }
        assertEquals(5, movies.size());
    }

    @Test
    void selectActorByBirthDate() {
        Short date = 1963;
        List<Person> actors = repository.searchByActorBirthDate(date);
        for (Person a : actors) {
            System.out.println(a);
        }
        assertTrue(actors.size() > 0);
    }

}