package com.norab.actor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Actor(
            resultSet.getInt("actor_id"),
            resultSet.getString("full_name"),
            LocalDate.parse(resultSet.getString("birth_date"))
        );
    }
}
