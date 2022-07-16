package com.norab.movie;

import com.norab.utils.DeleteResult;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(movies.size(), 6);
        assertEquals(movies.get(3).getTitleOriginal(), "The Gift");
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
        Movie movie = new Movie("Kleo", "Patra", (short) 2002);
        int movieId = repository.insertMovie(movie);
        assertEquals(DeleteResult.JDBC_ERROR, repository.deleteMovie(1));
        assertEquals(DeleteResult.INVALID_ID, repository.deleteMovie(2255));
        assertEquals(DeleteResult.SUCCESS, repository.deleteMovie(movieId));
    }

    @Test
    @Order(4)
    void selectMovieById() {
        Integer id = 2;
        Optional<Movie> selected = repository.selectMovieById(id);
        assertEquals(selected.get().getTitle(), "A Karib-tenger kalózai: Ismeretlen vizeken");

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
        assertEquals(mov.getTitleOriginal(), "Parenthood");
        assertEquals(mov.getTitle(), "A Karib-tenger kalózai: Ismeretlen vizeken");
        assertEquals(mov.getReleaseDate(), (short) 2011);

        Movie movie = new Movie(
            "Fontos vagy nekem",
            "I Care a Lot",
            (short) 2021);
        int result1 = repository.updateMovie(20022, movie);
        assertEquals(0, result1);

    }

}