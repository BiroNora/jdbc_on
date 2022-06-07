package com.norab.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovieDataAccessRepositoryTest {
    @Autowired
    MovieService service;
    @MockBean
    MovieDataAccessRepository repository;

    @Test
    void selectMovies() {
        when(repository.selectMovies()).thenReturn(Stream
            .of(new Movie(1L,
                    "Avatar",
                    "Avatar",
                    LocalDate.of(2009, Month.DECEMBER, 17)),
                new Movie(2L,
                    "Avatar2",
                    "Avatar2",
                    LocalDate.of(2029, Month.MARCH, 20))
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.selectMovies().toString());
        verify(repository).selectMovies();
        assertEquals(2, service.getMovies().size());
    }

    @Test
    void selectMovieByValidId() {
        Long id = 3L;
        when(repository.selectMovieById(id)).thenReturn(
            Optional.of(new Movie(1L,
                "Avatar",
                "Avatar",
                LocalDate.of(2009, Month.DECEMBER, 17))));

        assertNotNull(repository.selectMovieById(id));
        verify(repository).selectMovieById(id);
        assertEquals("Avatar", service.getMovie(id).title());
    }

    @Test
    void selectMovieByInvalidId() {
        Long id = 3L;
        when(repository.selectMovieById(id)).thenReturn(null);

        assertNull(repository.selectMovieById(id));
        verify(repository).selectMovieById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getMovie(id);
        });
    }

    @Test
    void insertMovie() {
        Movie movie = new Movie(1L,
            "Avatar",
            "Avatar",
            LocalDate.of(2009, Month.DECEMBER, 17));
        when(repository.insertMovie(movie)).thenReturn(1);

        assertEquals(1, repository.insertMovie(movie));
        verify(repository).insertMovie(movie);
        assertEquals(1, service.addNewMovie(movie));
    }

    @Test
    void deleteMovie() {
        Long id = 3L;
        repository.deleteMovie(id);
        verify(repository, times(1)).deleteMovie(id);
    }

    @Test
    void updateMovie() {
        Long id = 3L;
        Movie movie = new Movie(1L,
            "Avatar",
            "Avatar",
            LocalDate.of(2009, Month.DECEMBER, 17));
        when(repository.updateMovie(id, movie)).thenReturn(1);

        assertEquals(1, repository.updateMovie(id, movie));
        verify(repository).updateMovie(id, movie);
    }

}