package com.norab.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @Test
    void selectMovieByValidId() {
        Movie movie = new Movie(
            "Avatar",
            "Avatar",
            LocalDate.of(2009, Month.DECEMBER, 17));
        try {
            long id = repository.insertMovie(movie);
            var movie1 = repository.selectMovieById(id);
            assertTrue(movie1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Movie> expected = repository.selectMovies();

        assertNotNull(expected);
        for (Movie m : expected) {
            System.out.println(m.getId());
            System.out.println(m.getTitle());
        }
        assertTrue(expected.size() != 0);

    }

    @Test
    void selectMovieByInvalidId() {
    }

    @Test
    void selectMovies() {
    }

    @Test
    void insertMovie() {
    }

    @Test
    void deleteMovie() {

    }

    @Test
    void updateMovie() {
        Long id = 3L;
        Movie movie = new Movie(1L,
            "Avatar",
            "Avatar",
            LocalDate.of(2009, Month.DECEMBER, 17));

    }

}