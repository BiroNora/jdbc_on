package com.norab.actor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Short death_date = resultSet.getShort("death_date");

        return new Person(
            resultSet.getInt("actor_id"),
            resultSet.getString("full_name"),
            resultSet.getShort("birth_date"),
            death_date == null || death_date == 0 ? null : death_date
        );
    }
}
