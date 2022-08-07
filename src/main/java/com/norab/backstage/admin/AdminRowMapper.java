package com.norab.backstage.admin;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        /*return new Admin(
            rs.getString("adminId"),
            rs.getString("adminName"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("phone")
        );*/
        return null;
    }
}
