package com.norab.director;

import com.norab.exception.InvalidInputException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class DirectorRepositoryTest {
    @Autowired
    private DirectorRepository repository;

    @Test
    @Order(1)
    void selectDirectors() {
        List<Director> directors = repository.selectDirectors();
        for (Director d : directors) {
            System.out.println(d);
        }
        assertEquals(5, directors.size());
    }

    @Test
    @Order(2)
    void insertDirector() {
        Director dir1 = new Director(2, 5);
        int result = repository.insertDirector(dir1);
        assertEquals(1, result);

        Director dir2 = new Director(2, 4);
        result = repository.insertDirector(dir2);
        assertEquals(1, result);
    }

    @Test
    @Order(3)
    void insertDuplicateDirector() {
        Director dir = new Director(22, 1);
        int result = repository.insertDirector(dir);
        assertEquals(1, result);

        assertThrows(InvalidInputException.class, () -> repository.insertDirector(dir));
    }

    @Test
    @Order(4)
    void deleteDirector() {
        boolean b = repository.deleteDirector(5, 4);
        assertEquals(true, b);
    }

    @Test
    @Order(5)
    void selectDirectorById() {
        Optional<Director> director = repository.selectDirectorById(6, 3);
        assertTrue(director.isPresent());
    }

    @Test
    @Order(6)
    void selectMoviesByDirector() {
        List<DirectorDao.MoviesByDirector> moviesByDirectors
            = repository.selectMoviesByDirector("%L.%");
        for (DirectorDao.MoviesByDirector m : moviesByDirectors) {
            System.out.println(m);
        }
        assertEquals(1, moviesByDirectors.size());
    }

}