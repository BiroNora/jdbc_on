package com.norab.show.article;

import com.norab.backstage.user.User;
import com.norab.backstage.user.UserRepository;
import com.norab.show.movie.Movie;
import com.norab.show.movie.MovieRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ArticleRepositoryTest {
    static private User user;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @BeforeAll
    static void setUp() {
        user = new User("dmoppiuzt@nu", "wwwe", "user");
    }

    @Test
    void listAllArticlesByUsers() {
        /*UUID id = UUID.randomUUID();
        assertEquals("alma", id.toString());*/
    }

    @Test
    void listAllArticlesByMovies() {
    }

    @Test
    void insertArticle() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> {
            return userRepository.insertUser(user);
        });
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nullika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> {
            return articleRepository.insertArticle(art);
        });
    }

    @Test
    void deleteArticle() {
    }

    @Test
    void selectArticleById() {
    }

    @Test
    void selectArticlesByMovieId() {
    }

    @Test
    void selectArticlesByUserId() {
    }

    @Test
    void updateArticle() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> {
            return userRepository.insertUser(user);
        });
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nullika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> {
            return articleRepository.insertArticle(art);
        });

        Article art1 = new Article(uuid, "nullika", (short) 5, movieId);
        assertTrue(articleRepository.updateArticle(artId, art1));
    }
}
