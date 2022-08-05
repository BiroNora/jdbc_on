package com.norab.show.director;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorRowMapper implements RowMapper<Director> {
    @Override
    public Director mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Director(
            resultSet.getInt("actor_id"),
            resultSet.getInt("movie_id")
        );
    }
}
