package com.norab.actor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDataAccessService implements ActorDao {
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
        return jdbcTemplate.update(sql, actor.fullName(), actor.birthDate());
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
