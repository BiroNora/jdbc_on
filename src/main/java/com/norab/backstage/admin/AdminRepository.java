package com.norab.backstage.admin;

import com.norab.utils.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdminRepository implements AdminDao<Admin> {

    @Autowired
    //@Qualifier("admindb")
    private final JdbcTemplate adminJdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate, JdbcTemplate adminJdbcTemplate) {
        this.adminJdbcTemplate = adminJdbcTemplate;
    }

    @Override
    public List<String> listAdmins() {
        var sql = "select full_name from admins;";

        return adminJdbcTemplate.query(sql, (resultset, i) ->
            resultset.getString("full_name"));
    }

    @Override
    public int insertAdmin(Admin admin) throws IllegalStateException {
        return 0;
    }

    @Override
    public DeleteResult deleteAdmin(UUID adminId, boolean force) {
        return null;
    }

    @Override
    public Optional<Admin> selectAdminById(UUID adminId) {
        return Optional.empty();
    }

    @Override
    public List<Admin> selectAdminByName(String name, boolean match) {
        return null;
    }

    @Override
    public int updateAdmin(UUID adminId, Admin admin) {
        return 0;
    }
}
