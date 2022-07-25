package com.norab.crossed;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorsByMovieMapper implements RowMapper<ActorsByMovie> {
    @Override
    public ActorsByMovie mapRow(ResultSet resultSet, int i) throws SQLException {
        ActorsByMovie actor = new ActorsByMovie(
            resultSet.getString("title"),
            resultSet.getString("title_original"),
            resultSet.getString("full_name"),
            resultSet.getString("role_name")
        );
        return actor;
    }
}
