package com.norab.backstage.admin;

import com.norab.security.Roles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
            rs.getObject("admin_id", java.util.UUID.class),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("phone"),
            (List<Roles>) rs.getArray("role"),
            null,
            true,
            true,
            true,
            true
        );
    }
}
