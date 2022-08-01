package com.norab.actor;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepository implements ActorDao<Person> {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ActorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> listActors(Page page) {
        var sql = "SELECT actor_id, full_name, birth_date, death_date " +
            "FROM actors ORDER BY full_name asc LIMIT '" + page.getLimit() + "' " +
            "OFFSET '" + page.getOffset() + "'";
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
            if (actor.getBirthDate() != null) {
                ps.setShort(2, actor.getBirthDate());
            } else {
                ps.setNull(2, Types.SMALLINT);
            }
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
    public DeleteResult deleteActor(Integer actorId, boolean force) {
        var sql = """
            DELETE FROM actors
            WHERE actor_id = ?;
            """;
        String checkUsage = """
            SELECT sum(c) AS numOfRefs FROM
              (SELECT count(*) AS c FROM directors
              WHERE actor_id = ?
              UNION
              SELECT count(*) FROM plays
              WHERE actor_id = ?)
            ;
            """;
        try {
            if (!force) {
                List<Boolean> result = jdbcTemplate.query(
                    checkUsage,
                    (resultSet, i) -> resultSet.getInt("numOfRefs") > 0,
                    actorId, actorId);
                if (result.contains(true)) {
                    return DeleteResult.HAS_REFERENCES;
                }
            }
            int delete = jdbcTemplate.update(sql, actorId);
            if (delete == 0) {
                return DeleteResult.INVALID_ID;
            }
        } catch (DataAccessException e) {
            log.info(String.format("Actor with id: %d is failed to delete: %s", actorId, e.getMessage()));
            return DeleteResult.JDBC_ERROR;
        }
        log.info(String.format("Actor with id: %d is deleted.", actorId));
        return DeleteResult.SUCCESS;
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
    public List<Person> selectActorByName(String name, boolean match) {
        var sql = """
            SELECT actor_id, full_name, birth_date, death_date
            FROM actors
            WHERE LOWER(full_name) LIKE LOWER(?);
            """;
        String q = match ? Utils.addPercent(name) : name;
        return jdbcTemplate.query(sql, new ActorRowMapper(), q);
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
