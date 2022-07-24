package com.norab.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository repository;

    @Test
    void selectGenres() {
        List<String> genres = repository.selectAllGenre();
        for (String g : genres) {
            System.out.println(g);
        }
        assertTrue(genres.size() > 0);
    }

    @Test
    void selectAllGenre() {
        List<String> genres = repository.selectAllGenre();
        assertTrue(genres.size() > 0);
    }

    @Test
    void insertGenre_DeleteGenre() {
        Genre g1 = new Genre(5, "dramatic");
        boolean result = repository.insertGenre(g1);
        assertEquals(true, result);

        boolean b = repository.deleteGenre(g1.getMovieId(), g1.getGenre());
        assertTrue(b);
    }

    @Test
    void selectGenreById() {
        boolean exists = repository.selectGenreById(3, "comedy");
        assertTrue(exists);
    }

    @Test
    void selectMoviesByGenre() {
        List<GenreDao.MoviesByGenre> genres = repository.selectMoviesByGenre("%med%");
        for (GenreDao.MoviesByGenre g : genres) {
            System.out.println(g);
        }
        assertTrue(genres.size() > 0);
    }

    @Test
    void selectGenresByMovieId() {
        List<String> strings = repository.selectGenresByMovieId(5);
        for (String s : strings) {
            System.out.println(s);
        }
        assertTrue(strings.size() > 0);
    }
}
