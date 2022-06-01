package com.norab.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDataAccessRepository implements RoleDao<Plays> {
    private static final Logger log = LoggerFactory.getLogger(RoleDataAccessRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public RoleDataAccessRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Plays> selectRoles() {
        var sql = """
            SELECT role_id, role_name, movie_id, actor_id
            FROM plays
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    @Override
    public int insertRole(Plays plays) {
        var sql = """
            INSERT into plays(role_name, movie_id, actor_id) VALUES (?, ?, ?);
            """;
        int insert = jdbcTemplate.update(sql, plays.roleName(), plays.movieId(), plays.actorId());
        if (insert == 1) {
            log.info("New role inserted: " + plays);
        }
        return insert;
    }

    @Override
    public int deleteRole(int id) {
        var sql = """
            DELETE FROM plays
            WHERE role_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info(String.format("Role with id: %d is deleted.", id));
        }
        return delete;
    }

    @Override
    public Optional<Plays> selectRoleById(int id) {
        var sql = """
            SELECT role_id, role_name, movie_id, actor_id
            FROM plays
            WHERE role_id = ?;
            """;
        Optional<Plays> selected = jdbcTemplate.query(sql, new RoleRowMapper(), id)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Role with id: %d is selected.", id));
        }
        return selected;
    }

    @Override
    public int updateRole(int id, Plays plays) {
        var sql = """
            UPDATE plays
            SET role_name = ?, movie_id = ?, actor_id = ?
            WHERE role_id = ?;
            """;
        int update = jdbcTemplate.update(sql, plays.roleName(), plays.movieId(), plays.actorId(), plays.id());
        if (update == 1) {
            log.info(String.format("Role with id: %d is updated.", id));
        }
        return update;
    }
}
