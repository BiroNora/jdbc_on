package com.norab.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
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
            SELECT movie_id, title, title_original, release_date, movie_film
            FROM movies
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    /*@Override
    public int insertMovie(Movie movie) {
        var sql = """
            INSERT INTO movies(title, title_original, release_date) VALUES (?, ?, ?);
            """;
        int insert = jdbcTemplate.update(sql, movie.getTitle(), movie.getTitleOriginal(), movie.getReleaseDate());
        if (insert == 1) {
            log.info("New movie inserted: " + movie);
        }
        return insert;
    }*/

    @Override
    public int insertMovie(Movie movie) {
        var sql = """
            INSERT INTO movies(title, title_original, release_date, movie_film) VALUES (?, ?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"movie_id"});
            ps.setString(1, movie.getTitle());
            if (movie.getTitleOriginal() != null) {
                ps.setString(2, movie.getTitleOriginal());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            ps.setShort(3, movie.getReleaseDate());
            ps.setBoolean(4, movie.isMovieFilm());
            return ps;
        }, keyHolder);

        if (result != 1) {
            throw new IllegalStateException("Failed to add movie");
        }
        log.info("New movie inserted: " + movie);

        return keyHolder.getKeyAs(Integer.class);
    }

    @Override
    public int deleteMovie(Integer movieId) {
        var sql = """
            DELETE FROM movies
            WHERE movie_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, movieId);
        if (delete == 1) {
            log.info(String.format("Movie with id: %d is deleted.", movieId));
        }
        return delete;
    }

    @Override
    public Optional<Movie> selectMovieById(Integer movieId) {
        var sql = """
            SELECT movie_id, title, title_original, release_date, movie_film
            FROM movies
            WHERE movie_id = ?;
            """;
        Optional<Movie> selected = jdbcTemplate.query(sql, new MovieRowMapper(), movieId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Movie with id: %d is selected.", movieId));
        }
        return selected;
    }

    @Override
    public int updateMovie(Integer movieId, Movie movie) {
        var sql = """
            UPDATE movies
            SET title = ?, title_original = ?, release_date = ?, movie_film = ?
            WHERE movie_id = ?;
            """;
        int update = jdbcTemplate.update(
            sql,
            movie.getTitle(),
            movie.getTitleOriginal(),
            movie.getReleaseDate(),
            movie.isMovieFilm(),
            movie.getMovieId()
        );
        if (update == 1) {
            log.info(String.format("Movie with id: %d is updated.", movieId));
        }
        return update;
    }

}
