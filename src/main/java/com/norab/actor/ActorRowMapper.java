package com.norab.actor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException, DateTimeParseException {
        String death_date = resultSet.getString("death_date");

        return new Actor(
            resultSet.getLong("actor_id"),
            resultSet.getString("full_name"),
            LocalDate.parse(resultSet.getString("birth_date")),
            death_date != null && death_date.compareTo("") != 0 && death_date.compareTo("0000-00-00") !=0 ? LocalDate.parse(death_date) : null
        );
    }
}
