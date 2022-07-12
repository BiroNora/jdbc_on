package com.norab.director;

import com.norab.crossed.CrossedDao;
import com.norab.exception.InvalidInputException;
import com.norab.movie.Movie;
import com.norab.movie.MovieRowMapper;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepository implements DirectorDao<Director> {
    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public DirectorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Director> selectDirectors() {
        var sql = """
            SELECT actor_id, movie_id
            FROM directors
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new DirectorRowMapper());
    }

    @Override
    public int insertDirector(Director director) {
        var sql = """
            INSERT into directors(actor_id, movie_id) VALUES (?, ?);
            """;
        try {
            int update = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, director.getActorId());
                ps.setInt(2, director.getMovieId());
                return ps;
            });

            log.info("New director inserted: " + director);
            return update;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Illegal id");
        }
    }

    @Override
    public boolean deleteDirector(Integer actorId, Integer movieId) {
        var sql = """
            DELETE FROM directors
            WHERE actor_id = ? AND movie_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, actorId, movieId);
        if (delete == 1) {
            log.info(String.format("Director %d with movie %d is deleted.", actorId, movieId));
        }
        return delete == 1;
    }

    @Override
    public Optional<Director> selectDirectorById(Integer actorId, Integer movieId) {
        var sql = """
            SELECT actor_id, movie_id
            FROM directors
            WHERE actor_id = ? AND movie_id = ?;
            """;
        Optional<Director> selected = jdbcTemplate.query(sql, new DirectorRowMapper(), actorId, movieId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Director %d with movie %d is selected.", actorId, movieId));
        }
        return selected;
    }

    @Override
    public List<MoviesByDirector> selectMoviesByDirector(String name) {
        String q = Utils.addPercent(name);
        var sql = """
           SELECT title, title_original, release_date
           FROM movies
           JOIN
           ((SELECT actor_id, movie_id
           FROM directors) AS dir
           JOIN
           (SELECT actor_id, full_name
           FROM actors
           WHERE LOWER(full_name) LIKE LOWER(?)) AS act
           USING(actor_id))
           USING(movie_id)
            """;
        return jdbcTemplate.query(
            sql, (resultSet, i) -> new MoviesByDirector(
                resultSet.getString("title"),
                resultSet.getString("title_original"),
                LocalDate.parse(resultSet.getString("release_date"))), q);
    }

    @Override
    public Optional<Director> selectDirectorsByMovieId(Integer movieId) {
        var sql = """
            SELECT actor_id, movie_id
            FROM directors
            WHERE actor_id = ?;
            """;
        return Optional.empty();
    }

}
