package com.norab.director;

import com.norab.exception.InvalidInputException;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.norab.crossed.SearchLocation.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class DirectorRepositoryTest {
    @Autowired
    private DirectorRepository repository;

    @Test
    void listDirectors() {
        Page page = new Page(1, 10);
        List<ResultResponse> directors = repository.listDirectors(page);
        for (ResultResponse d : directors) {
            System.out.println(d);
        }
        assertTrue(directors.size() > 0);
    }

    @Test
    void listDirectorsAndMovies() {
        List<DirectorDao.Directors> directors = repository.listDirectorsAndMovies();
        for (DirectorDao.Directors d : directors) {
            System.out.println(d);
        }
        assertTrue(directors.size() > 0);
    }

    @Test
    void insertDirector() {
        Director dir1 = new Director(2, 5);
        boolean result = repository.insertDirector(dir1);
        assertTrue(result);

        Director dir2 = new Director(2, 4);
        result = repository.insertDirector(dir2);
        assertTrue(result);
    }

    @Test
    void insertDuplicateDirector() {
        Director dir = new Director(22, 3);
        boolean result = repository.insertDirector(dir);
        assertTrue(result);

        assertThrows(InvalidInputException.class, () -> repository.insertDirector(dir));
    }

    @Test
    void deleteDirector() {
        Director dir = new Director(12, 3);
        boolean result = repository.insertDirector(dir);
        assertTrue(result);
        boolean b = repository.deleteDirector(12, 3);
        assertTrue(b);
    }

    @Test
    void selectDirectorById() {
        boolean exists = repository.selectDirectorById(7, 5);
        assertTrue(exists);
    }

    @Test
    void selectDirectorByInvalidId() {
        boolean exists = repository.selectDirectorById(1, 5);
        assertFalse(exists);
    }

    @Test
    void selectMoviesByDirector() {
        List<DirectorDao.MoviesByDirector> moviesByDirectors
            = repository.selectMoviesByDirector("Giuseppe");
        for (DirectorDao.MoviesByDirector m : moviesByDirectors) {
            System.out.println(m);
        }
        assertTrue(moviesByDirectors.size() > 0);
    }

    @Test
    void selectDirectorsByMovieTitle() {
        List<ResultResponse> directors = repository.selectDirectorsByMovieTitle("%oss%", TITLE);
        assertTrue(directors.size() > 0);
    }

    @Test
    void selectDirectorsByMovieOriginalTitle() {
        List<ResultResponse> directors = repository.selectDirectorsByMovieTitle("%ood%", ORIGTITLE);
        assertTrue(directors.size() > 0);
    }

    @Test
    void selectDirectorsByMovieOriginalTitleAndTitle() {
        List<ResultResponse> directors = repository.selectDirectorsByMovieTitle("%ki%", ALL);
        assertTrue(directors.size() > 0);

        List<ResultResponse> directors1 = repository.selectDirectorsByMovieTitle("%off%", ALL);
        assertTrue(directors1.size() > 0);
    }
}
