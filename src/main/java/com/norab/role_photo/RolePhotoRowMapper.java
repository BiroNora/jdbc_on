package com.norab.role_photo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolePhotoRowMapper implements RowMapper<RolePhoto> {
    @Override
    public RolePhoto mapRow(ResultSet resultSet, int i) throws SQLException {
        return new RolePhoto(
            resultSet.getInt("photo_id"),
            resultSet.getString("url"),
            resultSet.getInt("role_id")
        );
    }
}
