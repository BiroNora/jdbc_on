package com.norab.show.article;

import com.norab.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ArticleRepository implements ArticleDao<Article> {
    private static final Logger log = LoggerFactory.getLogger(ArticleRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> listAllArticlesByUsers(Page page) {
        var sql = """
            SELECT
            art_id,
            user_id,
            body,
            rating,
            movie_id
            FROM articles
            ORDER BY user_id asc
            LIMIT ?
            OFFSET ?;
            """;
        return jdbcTemplate.query(sql, new ArticleRowMapper(), page.getLimit(), page.getOffset());
    }

    @Override
    public List<Article> listAllArticlesByMovies(Page page) {
        var sql = """
            SELECT 
            art_id, 
            user_id, 
            body, 
            rating, 
            movie_id 
            FROM articles
            ORDER BY movie_id asc 
            LIMIT ? 
            OFFSET ?;
            """;
        return jdbcTemplate.query(sql, new ArticleRowMapper(), page.getLimit(), page.getOffset());
    }

    @Override
    public int insertArticle(Article article) throws IllegalStateException {
        var sql = """
            INSERT INTO articles(
            user_id, 
            body, 
            rating, 
            movie_id 
            ) VALUES (?, ?, ?, ?)
            ;
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"art_id"});
            ps.setString(1, String.valueOf(article.getUserId()));
            if (article.getBody() != null) {
                ps.setString(2, article.getBody());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            ps.setInt(3, article.getStar());
            ps.setInt(4, article.getMovieId());
            return ps;
        }, keyHolder);

        if (result != 1) {
            throw new IllegalStateException("Failed to add article");
        }
        Integer id = keyHolder.getKeyAs(Integer.class);
        log.info("New article inserted: " + id);

        return id;
    }

    @Override
    public boolean deleteArticle(Integer artId) {
        var sql = """
            DELETE FROM articles
            WHERE art_id = ?;
            """;
        return jdbcTemplate.update(sql, artId) == 1;
    }

    @Override
    public Optional<Article> selectArticleById(Integer artId) {
        var sql = """
            SELECT * FROM articles
            WHERE art_id = ?;
            """;
        Optional<Article> selected = jdbcTemplate.query(sql, new ArticleRowMapper(), artId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Article with id: %d is selected.", artId));
        }
        return selected;
    }

    @Override
    public List<Article> selectArticlesByMovieId(Integer movieId, Page page) {
        var sql = """
            SELECT * FROM articles
            WHERE movie_id = ?
            ORDER BY movie_id
            LIMIT ? 
            OFFSET ?;
            """;
        return jdbcTemplate.query(
            sql,
            new ArticleRowMapper(),
            movieId,
            page.getLimit(),
            page.getOffset());
    }

    @Override
    public List<Article> selectArticlesByUserId(UUID userId, Page page) {
        var sql = """
            SELECT * FROM articles 
            WHERE user_id::text = ?
            ORDER BY movie_id
            LIMIT ?
            OFFSET ?;
            """;
        return jdbcTemplate.query(
            sql,
            new ArticleRowMapper(),
            userId.toString(),
            page.getLimit(),
            page.getOffset()
        );
    }

    @Override
    public boolean updateArticle(Integer artId, Article article) {
        var sql = """
            UPDATE articles
            SET
            body = ?,
            rating = ?            
            WHERE art_id = ? AND user_id::text = ? AND movie_id = ?;
            """;
        int update = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (article.getBody() != null) {
                ps.setString(1, article.getBody());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }
            ps.setShort(2, article.getStar());
            ps.setInt(3, artId);
            ps.setString(4, String.valueOf(article.getUserId()));
            ps.setInt(5, article.getMovieId());
            return ps;
        });
        if (update == 1) {
            log.info(String.format("Article with id: %d is updated.", artId));
        }
        return update == 1;
    }
}
