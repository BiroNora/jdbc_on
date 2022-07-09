package com.norab.actor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ActorRowMapper implements RowMapper<Actor> {

    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
        Actor actor = new Actor();
        String death_date = resultSet.getString("death_date");

        actor.setActorId(resultSet.getLong("actor_id"));
        actor.setFullName(resultSet.getString("full_name"));
        actor.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
        actor.setDeathDate(death_date == null
            || death_date.compareTo("") == 0
            || death_date.compareTo("0000-00-00") == 0
            ? null : LocalDate.parse(death_date));

        return actor;
    }
}
