package com.norab.role_photo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolePhotoRowMapper implements RowMapper<RolePhoto> {
    @Override
    public RolePhoto mapRow(ResultSet resultSet, int i) throws SQLException {
        return new RolePhoto(
            resultSet.getLong("photo_id"),
            resultSet.getString("url"),
            resultSet.getLong("movie_id"),
            resultSet.getLong("actor_id"),
            resultSet.getLong("role_id")
        );
    }
}
