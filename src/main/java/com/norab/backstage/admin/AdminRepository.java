package com.norab.backstage.admin;

import com.norab.show.actor.ActorRepository;
import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public int updateAdmin(UUID adminId, Admin admin) {
        return 0;
    }

    @Override
    public int insertAdmin(Admin admin) throws IllegalStateException {
        return 0;
    }

    @Override
    public DeleteResult deleteAdmin(UUID adminId, boolean force) {
        return null;
    }

}
