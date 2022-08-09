package com.norab.backstage.admin;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
            rs.getObject("admin_id", java.util.UUID.class),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("phone"),
            null,
            true,
            true,
            true,
            true
        );
    }
}
