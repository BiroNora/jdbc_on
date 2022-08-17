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
        /*UUID id = UUID.randomUUID();
        assertEquals("alma", id.toString());*/
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

        String userId = assertDoesNotThrow(() -> {
            return userRepository.insertUser(user);
        });
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
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

        String userId = assertDoesNotThrow(() -> {
            return userRepository.insertUser(user);
        });
        UUID uuid = UUID.fromString(userId);
        Article art = new Article(uuid, "nudlika", (short) 1, movieId);
        int artId = assertDoesNotThrow(() -> {
            return articleRepository.insertArticle(art);
        });

        Article art1 = new Article(uuid, "pacika", (short) 5, movieId);
        assertTrue(articleRepository.updateArticle(artId, art1));

        Optional<Article> updated = articleRepository.selectArticleById(artId);
        assertTrue(updated.isPresent());
        assertEquals((short)5, updated.get().getStar());
        assertEquals("pacika", updated.get().getBody());
    }
}
