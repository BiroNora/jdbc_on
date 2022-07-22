package com.norab.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository repository;

    @Test
    void selectGenres() {
    }

    @Test
    void selectAllGenre() {
        List<String> genres = repository.selectAllGenre();
        assertTrue(genres.size() > 0);
    }

    @Test
    void insertGenre() {
    }

    @Test
    void deleteGenre() {
    }

    @Test
    void selectGenreById() {
    }

    @Test
    void selectMoviesByGenre() {
    }

    @Test
    void selectGenresByMovieId() {
    }
}
