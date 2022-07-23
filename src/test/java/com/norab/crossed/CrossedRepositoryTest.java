package com.norab.crossed;

import com.norab.actor.ActorRepository;
import com.norab.actor.Person;
import com.norab.movie.Movie;
import com.norab.movie.MovieRepository;
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
    private CrossedRepository crossedRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void allMoviesByActor() {
        List<CrossedDao.MoviesByActor> movies = crossedRepository.allMoviesByActor(1);
        assertEquals(2, movies.size());
        assertEquals("A Karib-tenger kalózai: A Fekete Gyöngy átka", movies.get(0).title());
    }

    @Test
    void allMoviesByReleaseDateAsc() {
        List<Movie> movies = crossedRepository.allMoviesByReleaseDateAsc();
        for (Movie m : movies) {
            System.out.println(m);
        }
        assertTrue(movies.size() > 0);
    }

    @Test
    void selectActorByBirthDate() {
        Short date = 1963;
        List<Person> actors = crossedRepository.searchByActorBirthDate(date);
        for (Person a : actors) {
            System.out.println(a);
        }
        assertTrue(actors.size() > 0);
    }

}
