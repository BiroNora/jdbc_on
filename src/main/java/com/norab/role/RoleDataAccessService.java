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
    public List<Role> selectRoles() {
        var sql = """
            SELECT role_id, movie_id, actor_id, role_name
            FROM movie_role
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    @Override
    public int insertRole(Role role) {
        var sql = """
            INSERT into movie_role(movie_id, actor_id, role_name) VALUES (?, ?, ?);
            """;
        return jdbcTemplate.update(sql, role.movieId(), role.actorId(), role.roleName());
    }

    @Override
    public int deleteRole(int id) {
        var sql = """
            DELETE FROM movie_role
            WHERE role_id = ?;
            """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Role> selectRoleById(int id) {
        var sql = """
            SELECT role_id, movie_id, actor_id, role_name
            FROM movie_role
            WHERE role_id = ?;
            """;
        return jdbcTemplate.query(sql, new RoleRowMapper(), id)
            .stream()
            .findFirst();
    }

    @Override
    public int updateRole(int id, Role role) {
        var sql = """
            UPDATE movie_role
            SET role_name = ?
            WHERE role_id = ?;
            """;
        return jdbcTemplate.update(sql, role.roleName(), role.id());
    }
}
