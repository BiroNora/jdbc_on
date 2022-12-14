package com.norab.show.genre;

import com.norab.utils.ResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository repository;

    @Test
    void selectGenres() {
        List<GenreDao.GenresByMovieId> genres = repository.selectGenres();
        for (GenreDao.GenresByMovieId g : genres) {
            System.out.println(g);
        }
        assertTrue(genres.size() > 0);
    }

    @Test
    void selectAllGenre() {
        List<ResultResponse> genres = repository.selectAllGenre();
        assertTrue(genres.size() > 0);
    }

    @Test
    void insertGenre_DeleteGenre() {
        Genre g1 = new Genre(5, "dramatic");
        boolean result = repository.insertGenre(g1);
        assertTrue(result);

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
        List<ResultResponse> strings = repository.selectGenresByMovieId(5);
        for (ResultResponse s : strings) {
            System.out.println(s);
        }
        assertTrue(strings.size() > 0);
    }
}
