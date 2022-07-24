package com.norab.movie;

import com.norab.director.Director;
import com.norab.director.DirectorRepository;
import com.norab.utils.DeleteResult;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")
@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    void selectMovies() {
        List<Movie> movies = repository.selectMovies();
        for (Movie m : movies) {
            System.out.print(m.getMovieId() + " ");
            System.out.println(m.getTitle());
        }
        assertTrue(movies.size() > 0);
    }

    @Test
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
    void deleteMovie_InvalidId() {
        assertEquals(DeleteResult.INVALID_ID, repository.deleteMovie(2255, false));
        assertEquals(DeleteResult.INVALID_ID, repository.deleteMovie(2255, true));
    }

    @Test
    void deleteMovie_NoReference() {
        Movie movie = new Movie("Kleo", "Patra", (short) 2002);
        int movieId = repository.insertMovie(movie);
        assertEquals(DeleteResult.SUCCESS, repository.deleteMovie(movieId, false));

        movieId = repository.insertMovie(movie);
        assertEquals(DeleteResult.SUCCESS, repository.deleteMovie(movieId, true));
    }

    @Test
    void deleteMovie_WithReferences() {
        Movie movie = new Movie("Kleo", "Patra", (short) 2002);
        int movieId = repository.insertMovie(movie);
        Director dir = new Director(22, movieId);
        boolean result = directorRepository.insertDirector(dir);
        assertTrue(result);
        assertEquals(DeleteResult.HAS_REFERENCES, repository.deleteMovie(movieId, false));
        assertEquals(DeleteResult.SUCCESS, repository.deleteMovie(movieId, true));
    }

    @Test
    void selectMovieById() {
        Integer id = 2;
        Optional<Movie> selected = repository.selectMovieById(id);
        assertEquals(selected.get().getTitle(), "A Karib-tenger kalózai: Ismeretlen vizeken");

        Integer id1 = 2144;
        Optional<Movie> selected1 = repository.selectMovieById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
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
