package com.norab.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {
    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        var sql = """
            SELECT movie_id, title, release_date, picture
            FROM movie
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        var sql = """
            INSERT INTO movie(title, release_date, picture) VALUES (?, ?, ?);
            """;
        return jdbcTemplate.update(sql, movie.title(), movie.releaseDate(), movie.picture());
    }

    @Override
    public int deleteMovie(int id) {
        var sql = """
            DELETE FROM movie
            WHERE movie_id = ?;
            """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        var sql = """
            SELECT movie_id, title, release_date, picture
            FROM movie
            WHERE movie_id = ?;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), id)
            .stream()
            .findFirst();
    }

    @Override
    public int updateMovie(int id, Movie movie) {
        var sql = """
            UPDATE movie 
            SET title = ?, release_date = ?, picture = ?
            WHERE movie_id = ?;
            """;
        return jdbcTemplate.update(sql, movie.title(), movie.releaseDate(), movie.picture(), movie.id());
    }

}

