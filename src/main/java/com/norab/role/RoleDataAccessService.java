package com.norab.role;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDataAccessService implements RoleDao {
    private final JdbcTemplate jdbcTemplate;

    public RoleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Plays> selectRoles() {
        var sql = """
            SELECT role_id, movie_id, actor_id, role_name
            FROM plays
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    @Override
    public int insertRole(Plays plays) {
        var sql = """
            INSERT into plays(movie_id, actor_id, role_name) VALUES (?, ?, ?);
            """;
        return jdbcTemplate.update(sql, plays.movieId(), plays.actorId(), plays.roleName());
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
            SELECT role_id, movie_id, actor_id, role_name
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
            SET role_name = ?
            WHERE role_id = ?;
            """;
        return jdbcTemplate.update(sql, plays.roleName(), plays.id());
    }
}
