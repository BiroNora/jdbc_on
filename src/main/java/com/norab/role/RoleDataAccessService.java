package com.norab.role;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDataAccessService implements RoleDao<Plays> {
    private final JdbcTemplate jdbcTemplate;

    public RoleDataAccessService(JdbcTemplate jdbcTemplate) {
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
        return jdbcTemplate.update(sql, plays.roleName(), plays.movieId(), plays.actorId());
    }

    @Override
    public int deleteRole(int id) {
        var sql = """
            DELETE FROM plays
            WHERE role_id = ?;
            """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Plays> selectRoleById(int id) {
        var sql = """
            SELECT role_id, role_name, movie_id, actor_id
            FROM plays
            WHERE role_id = ?;
            """;
        return jdbcTemplate.query(sql, new RoleRowMapper(), id)
            .stream()
            .findFirst();
    }

    @Override
    public int updateRole(int id, Plays plays) {
        var sql = """
            UPDATE plays
            SET role_name = ?, movie_id = ?, actor_id = ?
            WHERE role_id = ?;
            """;
        return jdbcTemplate.update(sql, plays.roleName(), plays.movieId(), plays.actorId(), plays.id());
    }
}
