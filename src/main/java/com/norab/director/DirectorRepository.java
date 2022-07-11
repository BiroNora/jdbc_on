package com.norab.director;

import com.norab.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"actor_id"});
                ps.setInt(1, director.getActorId());
                ps.setInt(2, director.getMovieId());
                return ps;
            }, keyHolder);

            log.info("New director inserted: " + director);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Illegal id");
        }
        return keyHolder.getKeyAs(Integer.class);
    }

    @Override
    public boolean deleteDirector(Integer actorId) {
        var sql = """
            DELETE FROM directors
            WHERE actor_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, actorId);
        if (delete == 1) {
            log.info(String.format("Director with id: %d is deleted.", actorId));
        }
        return delete == 1;
    }

    @Override
    public Optional<Director> selectDirectorById(Integer actorId) {
        var sql = """
            SELECT actor_id, movie_id
            FROM directors
            WHERE actor_id = ?;
            """;
        Optional<Director> selected = jdbcTemplate.query(sql, new DirectorRowMapper(), actorId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Director with id: %d is selected.", actorId));
        }
        return selected;
    }

    @Override
    public boolean updateDirector(Integer actorId, Director director) {
        var sql = """
            UPDATE directors
            SET actor_id = ?, movie_id = ?
            WHERE actor_id = ?;
            """;
        try {
            int update = jdbcTemplate.update(
                sql,
                director.getActorId(),
                director.getMovieId(),
                actorId);
            if (update == 1) {
                log.info(String.format("Director with id: %d is updated.", actorId));
            }
            return update == 1;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Invalid ID");
        }
    }
}
