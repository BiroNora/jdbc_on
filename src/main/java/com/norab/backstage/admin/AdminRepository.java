package com.norab.backstage.admin;

import com.norab.show.actor.ActorRepository;
import com.norab.utils.Page;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdminRepository implements AdminDao<Admin> {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);
    @Autowired
    private final JdbcTemplate adminJdbcTemplate;

    public AdminRepository(@Qualifier("adminJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.adminJdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Admin> listAdmins(Page page) {
        var sql = "SELECT admin_id, full_name, email, password, phone " +
            "FROM admins ORDER BY full_name LIMIT '" + page.getLimit() + "' " +
            "OFFSET '" + page.getOffset() + "'";
        return adminJdbcTemplate.query(sql, new AdminRowMapper());
    }

    @Override
    public Optional<Admin> selectAdminById(UUID adminId) {
        var sql = """
            SELECT admin_id, full_name, email, password, phone
            FROM admins WHERE admin_id = ?;
            """;
        return adminJdbcTemplate.query(sql, new AdminRowMapper(), adminId)
            .stream()
            .findFirst();
    }

    @Override
    public List<Admin> selectAdminByName(String name, boolean match) {
        var sql = """
            SELECT admin_id, full_name, email, password, phone
            FROM admins 
            WHERE LOWER(full_name) LIKE LOWER(?);
            """;
        String q = match ? Utils.addPercent(name) : name;
        return adminJdbcTemplate.query(sql, new AdminRowMapper(), q);
    }

    @Override
    public Admin adminByName(String name) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public int updateAdmin(UUID adminId, Admin admin) {
        var sql = """
            UPDATE admins
            SET full_name = ?, email = ?, password = ?, phone = ?
            WHERE admin_id = ?;
            """;
        int update = adminJdbcTemplate.update(sql, admin.getFullName(), admin.getEmail(), admin.getPassword(), admin.getPhone(), admin.getAdminId());
        if (update == 1) {
            log.info("Admin with id: " + adminId + " is updated.");
        }
        return update;
    }

    @Override
    public String insertAdmin(Admin admin) throws IllegalStateException {
        var sql = """
            INSERT INTO admins(full_name, email, password, phone) VALUES(?, ?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = adminJdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"admin_id"});
            ps.setString(1, admin.getFullName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getPhone());
            return ps;
        }, keyHolder);
        if (update == 1) {
            log.info("Admin with id: " + admin.getAdminId() + " is inserted.");
        }
        return String.valueOf(keyHolder.getKeyList().get(0).get("admin_id"));
    }

    @Override
    public int deleteAdmin(UUID adminId) {
        var sql = """
            DELETE FROM admins
            WHERE admin_id = ?;
            """;
        int delete = adminJdbcTemplate.update(sql, adminId);
        if (delete == 1) {
            log.info("Role with id: " + adminId + " is deleted.");
        }
        return delete;
    }

}
