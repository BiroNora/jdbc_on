package com.norab.movie;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @Test
    @Order(1)
    void selectMovies() {
        List<Movie> movies = repository.selectMovies();
        for (Movie m : movies) {
            System.out.print(m.getId() + " ");
            System.out.println(m.getTitle());
        }
        assertEquals(movies.size(), 2);
        assertEquals(movies.get(1).getTitleOriginal(), "Little Miss Sunshine");
    }

    @Test
    @Order(2)
    void insertMovie() {
        Movie movie = new Movie(
            "Avatar",
            "Avatar",
            LocalDate.of(2009, Month.DECEMBER, 17),
            true);
        long id1 = repository.insertMovie(movie);
        var movie1 = repository.selectMovieById(id1);
        assertTrue(movie1.isPresent());
        assertEquals(movie1.get().getId(), 3);
    }

    @Test
    @Order(3)
    void deleteMovie() {
        Long id = 1L;
        int result = repository.deleteMovie(id);
        assertEquals(1, result);

        Long id1 = 2245L;
        int result1 = repository.deleteMovie(id1);
        assertEquals(0, result1);
    }

    @Test
    @Order(4)
    void selectMovieById() {
        Long id = 2L;
        Optional<Movie> selected = repository.selectMovieById(id);
        assertEquals(selected.get().getTitleOriginal(), "Little Miss Sunshine");

        Long id1 = 2144L;
        Optional<Movie> selected1 = repository.selectMovieById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(5)
    void updateMovie() {
        Movie mov = repository.selectMovieById(2L).get();
        mov.setTitleOriginal("Parenthood");
        int result = repository.updateMovie(2L, mov);
        assertEquals(1, result);
        assertNotEquals(mov.getTitleOriginal(), "Little Miss Sunshine");
        assertEquals(mov.getTitle(), "A család kicsi kincse");
        assertEquals(mov.getReleaseDate(), LocalDate.of(2007, Month.FEBRUARY, 22));

        Movie movie = new Movie(
            "Fontos vagy nekem",
            "I Care a Lot",
            LocalDate.of(2021, Month.FEBRUARY, 19),
            false);
        int result1 = repository.updateMovie(20022L, movie);
        assertEquals(0, result1);

    }

}