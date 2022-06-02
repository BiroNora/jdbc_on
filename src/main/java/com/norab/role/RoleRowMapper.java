package com.norab.role;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Plays> {
    @Override
    public Plays mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Plays(
            resultSet.getLong("role_id"),
            resultSet.getString("role_name"),
            resultSet.getLong("movie_id"),
            resultSet.getLong("actor_id")

        );
    }
}
