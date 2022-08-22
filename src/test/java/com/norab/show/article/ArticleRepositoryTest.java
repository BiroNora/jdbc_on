package com.norab.show.article;

import com.norab.backstage.user.User;
import com.norab.backstage.user.UserRepository;
import com.norab.show.movie.Movie;
import com.norab.show.movie.MovieRepository;
import com.norab.utils.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ArticleRepositoryTest {
    static private User user;
    static private Page page;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @BeforeAll
    static void setUp() {
        user = new User("dmoppiuzt@nu", "wwwe", "user");
        page = new Page(1, 10);
    }

    @Test
    void listAllArticlesByUsers() {
        List<Article> articles = articleRepository.listAllArticlesByUsers(page);
        for (Article a : articles) {
            System.out.println(a.getArtId() + " + " + a.getUserId());
        }
        assertTrue(articles.size() > 0);
    }

    @Test
    void listAllArticlesByMovies() {
        List<Article> articles = articleRepository.listAllArticlesByMovies(page);
        for (Article a : articles) {
            System.out.println(a.getArtId() + ": " + a.getMovieId());
        }
        assertTrue(articles.size() > 0);
    }

    @Test
    void insertArticle() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> userRepository.insertUser(user));
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> articleRepository.insertArticle(art));
        assertTrue(artId > 0);
    }

    @Test
    void deleteArticle() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> userRepository.insertUser(user));
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> articleRepository.insertArticle(art));
        assertTrue(artId > 0);

        boolean b = articleRepository.deleteArticle(artId);
        assertTrue(b);
    }

    @Test
    void selectArticleById() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> userRepository.insertUser(user));
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> articleRepository.insertArticle(art));
        assertTrue(artId > 0);

        Optional<Article> selected = articleRepository.selectArticleById(artId);
        assertTrue(selected.isPresent());
        art.setArtId(artId);
        assertEquals(art, selected.get());
    }

    @Test
    void selectArticlesByMovieId() {
        List<Article> articles = articleRepository.selectArticlesByMovieId(2, page);
        for (Article a : articles) {
            System.out.println(a.getMovieId() + ": " + a.getArtId());
        }
        assertTrue(articles.size() > 0);
    }

    @Test
    void selectArticlesByUserId() {
        UUID uuid = UUID.fromString("a7c303ad-bc27-4175-aa02-cc53f4f68045");
        List<Article> articles = articleRepository.selectArticlesByUserId(uuid, page);
        for (Article a : articles) {
            System.out.println(a.getUserId() + ": " + a.getArtId());
        }
        assertTrue(articles.size() > 0);
    }

    @Test
    void updateArticle() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> userRepository.insertUser(user));
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> articleRepository.insertArticle(art));

        Article desired = new Article(artId, uuid, "pacika", (short) 5, movieId);
        assertTrue(articleRepository.updateArticle(artId, desired));

        Optional<Article> updated = articleRepository.selectArticleById(artId);
        assertTrue(updated.isPresent());
        assertEquals(desired, updated.get());
    }

    @Test
    void updateArticle_WithNullBody() {
        int movieId = 1;
        Optional<Movie> movie = movieRepository.selectMovieById(movieId);
        assertTrue(movie.isPresent());

        String userId = assertDoesNotThrow(() -> userRepository.insertUser(user));
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> articleRepository.insertArticle(art));

        Article desired = new Article(artId, uuid, null, (short) 5, movieId);
        assertTrue(articleRepository.updateArticle(artId, desired));

        Optional<Article> updated = articleRepository.selectArticleById(artId);
        System.out.println(updated);
        assertTrue(updated.isPresent());
        assertEquals(desired, updated.get());
    }
}
