package com.norab.actor;

import com.norab.movie.Movie;
import com.norab.movie.MovieRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ActorRepository implements ActorDao<Actor> {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActorRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> allMoviesByActor(Long id) {
        var sql = """
            SELECT role_name, movies.movie_id, title, title_original, release_date
            FROM movies
            JOIN
            (SELECT role_name, movie_id
            FROM plays
            WHERE actor_id = ?) AS p
            USING (movie_id)
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), id);
    }

    @Override
    public List<Actor> selectActors() {
        var sql = """
            SELECT actor_id, full_name, birth_date, death_date
            FROM actors
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public long insertActor(Actor actor) {
        var sql = """
            INSERT INTO actors(full_name, birth_date, death_date) VALUES(?, ?, ?);                        
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"actor_id"});
            ps.setString(1, actor.getFullName());
            ps.setDate(2, Date.valueOf(actor.getBirthDate()));
            if (actor.getDeathDate() != null) {
                ps.setDate(3, Date.valueOf(actor.getDeathDate()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            return ps;
        }, keyHolder);

        if (result != 1) {
            throw new IllegalStateException("Failed to add actor");
        }
        log.info("New actor inserted: " + actor);

        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public int deleteActor(Long id) {
        var sql = """
            DELETE FROM actors
            WHERE actor_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info(String.format("Actor with id: %d is deleted.", id));
        }
        return delete;
    }

    @Override
    public Optional<Actor> selectActorById(Long id) {
        var sql = """
            SELECT actor_id, full_name, birth_date, death_date
            FROM actors
            WHERE actor_id = ?;
            """;
        Optional<Actor> selected = jdbcTemplate.query(sql, new ActorRowMapper(), id)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Actor with id: %d is selected.", id));
        }
        return selected;
    }

    @Override
    public int updateActor(Long id, Actor actor) {
        var sql = """
            UPDATE actors
            SET full_name = ?, birth_date = ?, death_date = ?
            WHERE actor_id = ?;
            """;
        int update = jdbcTemplate.update(sql, actor.getFullName(), actor.getBirthDate(), actor.getDeathDate(), actor.getId());
        if (update == 1) {
            log.info(String.format("Actor with id: %d is updated.", id));
        }
        return update;
    }
}
