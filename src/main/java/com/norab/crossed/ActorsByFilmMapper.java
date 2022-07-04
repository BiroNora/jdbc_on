package com.norab.crossed;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorsByFilmMapper implements RowMapper<ActorsByFilm> {
    @Override
    public ActorsByFilm mapRow(ResultSet resultSet, int i) throws SQLException {
        ActorsByFilm actor = new ActorsByFilm(
            resultSet.getString("title"),
            resultSet.getString("title_original"),
            resultSet.getString("full_name"),
            resultSet.getString("role_name")
        );
        return actor;
    }
}
