package com.norab.show.photo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoRowMapper implements RowMapper<Photo> {
    @Override
    public Photo mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Photo(
            resultSet.getInt("photo_id"),
            resultSet.getString("url"),
            resultSet.getInt("movie_id"),
            resultSet.getInt("actor_id"),
            resultSet.getInt("role_id")
        );
    }
}
