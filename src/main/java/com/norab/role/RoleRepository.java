package com.norab.role;

import com.norab.exception.InvalidInputException;
import com.norab.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RoleRepository implements RoleDao<Plays> {
    private static final Logger log = LoggerFactory.getLogger(RoleRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
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
    public long insertRole(Plays plays) {
        var sql = """
            INSERT into plays(role_name, movie_id, actor_id) VALUES (?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            int result = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"role_id"});
                ps.setString(1, plays.getRoleName());
                if (plays.getMovieId() != null) {
                    ps.setLong(2, plays.getMovieId());
                } else {
                    ps.setNull(2, Types.BIGINT);
                }
                if (plays.getActorId() != null) {
                    ps.setLong(3, plays.getActorId());
                } else {
                    ps.setNull(3, Types.BIGINT);
                }
                return ps;
            }, keyHolder);

            if (result != 1) {
                throw new NotFoundException("Failed to add new role");
            }
            log.info("New role inserted: " + plays);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new NotFoundException("Illegal id");
        }
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public int deleteRole(Long roleId) {
        var sql = """
            DELETE FROM plays
            WHERE role_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, roleId);
        if (delete == 1) {
            log.info(String.format("Role with id: %d is deleted.", roleId));
        }
        return delete;
    }

    @Override
    public Optional<Plays> selectRoleById(Long roleId) {
        var sql = """
            SELECT role_id, role_name, movie_id, actor_id
            FROM plays
            WHERE role_id = ?;
            """;
        Optional<Plays> selected = jdbcTemplate.query(sql, new RoleRowMapper(), roleId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Role with id: %d is selected.", roleId));
        }
        return selected;
    }

    @Override
    public int updateRole(Long roleId, Plays plays) throws InvalidInputException {
        var sql = """
            UPDATE plays
            SET role_name = ?, movie_id = ?, actor_id = ?
            WHERE role_id = ?;
            """;
        try {
            int update = jdbcTemplate.update(
                sql,
                plays.getRoleName(),
                plays.getMovieId(),
                plays.getActorId(),
                roleId);
            if (update == 1) {
                log.info(String.format("Role with id: %d is updated.", roleId));
            }
            return update;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Invalid ID");
        }
    }

}
