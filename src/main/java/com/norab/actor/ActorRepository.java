package com.norab.actor;

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
public class ActorRepository implements ActorDao<Person> {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActorRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> selectActors() {
        var sql = """
            SELECT actor_id, full_name, birth_date, death_date
            FROM actors
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public int insertActor(Person actor) {
        var sql = """
            INSERT INTO actors(full_name, birth_date, death_date) VALUES(?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"actor_id"});
            ps.setString(1, actor.getFullName());
            ps.setShort(2, actor.getBirthDate());
            if (actor.getDeathDate() != null) {
                ps.setShort(3, actor.getDeathDate());
            } else {
                ps.setNull(3, Types.SMALLINT);
            }
            return ps;
        }, keyHolder);

        if (result != 1) {
            throw new IllegalStateException("Failed to add actor");
        }
        log.info("New actor inserted: " + actor);

        return keyHolder.getKeyAs(Integer.class);
    }

    @Override
    public int deleteActor(Integer actorId) {
        var sql = """
            DELETE FROM actors
            WHERE actor_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, actorId);
        if (delete == 1) {
            log.info(String.format("Actor with id: %d is deleted.", actorId));
        }
        return delete;
    }

    @Override
    public Optional<Person> selectActorById(Integer actorId) {
        var sql = """
            SELECT actor_id, full_name, birth_date, death_date
            FROM actors
            WHERE actor_id = ?;
            """;
        Optional<Person> selected = jdbcTemplate.query(sql, new ActorRowMapper(), actorId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Actor with id: %d is selected.", actorId));
        }
        return selected;
    }

    @Override
    public Optional<Person> selectActorByName(String name) {
        var sql = """
            SELECT actor_id, full_name
            FROM actors
            WHERE LOWER(full_name) LIKE LOWER(?);
            """;
        Optional<Person> selected = jdbcTemplate.query(sql, new ActorRowMapper(), name)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Actor with id: %d is selected.", name));
        }
        return selected;
    }

    @Override
    public int updateActor(Integer actorId, Person actor) {
        var sql = """
            UPDATE actors
            SET full_name = ?, birth_date = ?, death_date = ?
            WHERE actor_id = ?;
            """;
        int update = jdbcTemplate.update(sql, actor.getFullName(), actor.getBirthDate(), actor.getDeathDate(), actor.getActorId());
        if (update == 1) {
            log.info(String.format("Actor with id: %d is updated.", actorId));
        }
        return update;
    }
}
