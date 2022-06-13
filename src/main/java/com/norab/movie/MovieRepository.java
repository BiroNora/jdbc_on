package com.norab.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository implements MovieDao<Movie> {
    private static final Logger log = LoggerFactory.getLogger(MovieRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        var sql = """
            SELECT movie_id, title, title_original, release_date
            FROM movies
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        var sql = """
            INSERT INTO movies(title, title_original, release_date) VALUES (?, ?, ?);
            """;
        int insert = jdbcTemplate.update(sql, movie.title(), movie.titleOriginal(), movie.releaseDate());
        if (insert == 1) {
            log.info("New movie inserted: " + movie);
        }
        return insert;
    }

    @Override
    public int deleteMovie(Long id) {
        var sql = """
            DELETE FROM movies
            WHERE movie_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info(String.format("Movie with id: %d is deleted.", id));
        }
        return delete;
    }

    @Override
    public Optional<Movie> selectMovieById(Long id) {
        var sql = """
            SELECT movie_id, title, title_original, release_date
            FROM movies
            WHERE movie_id = ?;
            """;
        Optional<Movie> selected = jdbcTemplate.query(sql, new MovieRowMapper(), id)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Movie with id: %d is selected.", id));
        }
        return selected;
    }

    @Override
    public int updateMovie(Long id, Movie movie) {
        var sql = """
            UPDATE movies
            SET title = ?, title_original = ?, release_date = ?
            WHERE movie_id = ?;
            """;
        int update = jdbcTemplate.update(sql, movie.title(), movie.titleOriginal(), movie.releaseDate(), movie.id());
        if (update == 1) {
            log.info(String.format("Movie with id: %d is updated.", id));
        }
        return update;
    }

}

