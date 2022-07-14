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
            System.out.print(m.getMovieId() + " ");
            System.out.println(m.getTitle());
        }
        assertEquals(movies.size(), 5);
        assertEquals(movies.get(4).getTitleOriginal(), "The Gift");
    }

    @Test
    @Order(2)
    void insertMovie() {
        Movie movie = new Movie(
            "Avatar",
            "Avatar",
            (short) 2009);
        int id1 = repository.insertMovie(movie);
        var movie1 = repository.selectMovieById(id1);
        assertTrue(movie1.isPresent());
        assertEquals(id1, movie1.get().getMovieId());
    }

    @Test
    @Order(3)
    void deleteMovie() {
        Integer id = 1;
        int result = repository.deleteMovie(id);
        assertEquals(1, result);

        Integer id1 = 2245;
        int result1 = repository.deleteMovie(id1);
        assertEquals(0, result1);
    }

    @Test
    @Order(4)
    void selectMovieById() {
        Integer id = 2;
        Optional<Movie> selected = repository.selectMovieById(id);
        assertEquals(selected.get().getTitleOriginal(), "Little Miss Sunshine");

        Integer id1 = 2144;
        Optional<Movie> selected1 = repository.selectMovieById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(5)
    void updateMovie() {
        Movie mov = repository.selectMovieById(2).get();
        mov.setTitleOriginal("Parenthood");
        int result = repository.updateMovie(2, mov);
        assertEquals(1, result);
        assertNotEquals(mov.getTitleOriginal(), "Little Miss Sunshine");
        assertEquals(mov.getTitle(), "A család kicsi kincse");
        assertEquals(mov.getReleaseDate(), (short) 2007);

        Movie movie = new Movie(
            "Fontos vagy nekem",
            "I Care a Lot",
            (short) 2021);
        int result1 = repository.updateMovie(20022, movie);
        assertEquals(0, result1);

    }

}