package com.norab.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDataAccessService implements ActorDao<Actor> {
    private static final Logger log = LoggerFactory.getLogger(ActorDataAccessService.class);
    private final JdbcTemplate jdbcTemplate;

    public ActorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> selectActors() {
        var sql = """
            SELECT actor_id, full_name, birth_date
            FROM actor
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public int insertActor(Actor actor) {
        var sql = """
            INSERT INTO actor(full_name, birth_date) VALUES(?, ?);                        
            """;
        int insert = jdbcTemplate.update(sql, actor.fullName(), actor.birthDate());
        if (insert == 1) {
            log.info("New Actor Created: " + actor.fullName());
        }
        return insert;
    }

    @Override
    public int deleteActor(int id) {
        var sql = """
            DELETE FROM actor
            WHERE actor_id = ?;
            """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Actor> selectActorById(int id) {
        var sql = """
            SELECT actor_id, full_name, birth_date
            FROM actor
            WHERE actor_id = ?;
            """;
        log.info("First Actor is: " + new ActorRowMapper());
        return jdbcTemplate.query(sql, new ActorRowMapper(), id)
            .stream()
            .findFirst();
    }

    @Override
    public int updateActor(int id, Actor actor) {
        var sql = """
            UPDATE actor
            SET full_name = ?, birth_date = ?
            WHERE actor_id = ?;
            """;
        return jdbcTemplate.update(sql, actor.fullName(), actor.birthDate(), actor.id());
    }
}
