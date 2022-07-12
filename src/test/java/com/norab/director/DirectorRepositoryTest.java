package com.norab.director;

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
        assertEquals(7, directors.size());
    }

    @Test
    @Order(2)
    void insertDirector() {
        Director dir1 = new Director(2, 8);
        int result = repository.insertDirector(dir1);
        assertEquals(1, result);

        Director dir2 = new Director(2, 7);
        result = repository.insertDirector(dir2);
        assertEquals(1, result);

        /*Director dir3 = new Director(1, 1);
        result = repository.insertDirector(dir3);
        assertEquals(0, result);*/
    }

    @Test
    @Order(3)
    void deleteDirector() {
        boolean b = repository.deleteDirector(1, 1);
        assertEquals(true, b);
    }

    @Test
    @Order(4)
    void selectDirectorById() {
        Optional<Director> director = repository.selectDirectorById(1, 9);
        assertTrue(director.isPresent());
    }

    @Test
    @Order(5)
    void selectMoviesByDirector() {
        List<DirectorDao.MoviesByDirector> moviesByDirectors
            = repository.selectMoviesByDirector("%ohnn%");
        for (DirectorDao.MoviesByDirector m : moviesByDirectors) {
            System.out.println(m);
        }
        assertEquals(3, moviesByDirectors.size());
    }

}