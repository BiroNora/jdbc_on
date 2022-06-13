package com.norab.photo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoRowMapper implements RowMapper<Photo> {
    @Override
    public Photo mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Photo(
            resultSet.getLong("photo_id"),
            resultSet.getString("url"),
            resultSet.getLong("movie_id"),
            resultSet.getLong("actor_id"),
            resultSet.getLong("role_id")
        );
    }
}
