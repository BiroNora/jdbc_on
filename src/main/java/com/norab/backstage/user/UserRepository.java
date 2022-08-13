package com.norab.backstage.user;

import com.norab.backstage.auth.ApplicationUserDao;
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
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userRepository")
public class UserRepository implements UserDao<User>, ApplicationUserDao {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);
    @Autowired
    private final JdbcTemplate userJdbcTemplate;

    public UserRepository(@Qualifier("userJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.userJdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listUsers(Page page) {
        var sql = """
            SELECT user_id, full_name, email, password, phone, roles,
            isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
            FROM users
            ORDER BY full_name
            LIMIT ?
            OFFSET ?;
            """;
        return userJdbcTemplate.query(sql, new UserRowMapper(), page.getLimit(), page.getOffset());
    }

    @Override
    public Optional<User> selectUserById(UUID userId) {
        var sql = """
            SELECT user_id, full_name, email, password, phone, roles,
            isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
            FROM users WHERE user_id = ?;
            """;
        return userJdbcTemplate.query(sql, new UserRowMapper(), userId)
            .stream()
            .findFirst();
    }

    @Override
    public List<User> selectUserByName(String name, boolean match) {
        var sql = """
            SELECT user_id, full_name, email, password, phone, roles,
            isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
            FROM users
            WHERE LOWER(full_name) LIKE LOWER(?);
            """;
        String q = match ? Utils.addPercent(name) : name;
        return userJdbcTemplate.query(sql, new UserRowMapper(), q);
    }

    @Override
    public Optional<User> selectUserByName(String name) {
        var sql = """
            SELECT user_id, full_name, email, password, phone, roles,
            isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
            FROM users
            WHERE LOWER(full_name) = LOWER(?);
            """;
        List<User> users = userJdbcTemplate.query(sql, new UserRowMapper(), name);
        if (users.size() != 1) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public boolean updateUser(UUID userId, User user) {
        var sql = """
            UPDATE users
            SET full_name = ?, email = ?, password = ?, phone = ?, roles = ?,
            isAccountNonExpired = ?, isAccountNonLocked = ?, isCredentialsNonExpired = ?, isEnabled = ?
            WHERE user_id = ?;
            """;
        int update = userJdbcTemplate.update(
            sql,
            user.getFullName(),
            user.getEmail(),
            user.getPassword(),
            user.getPhone(),
            user.getRolesAsString(),
            user.isAccountNonExpired(),
            user.isAccountNonLocked(),
            user.isCredentialsNonExpired(),
            user.isEnabled(),
            userId);
        if (update == 1) {
            log.info("User with id: " + userId + " is updated.");
        }
        return update == 1;
    }

    @Override
    public String insertUser(User user) throws IllegalStateException {
        var sql = """
            INSERT INTO users(
            full_name,
            email,
            password,
            phone,
            roles,
            isAccountNonExpired,
            isAccountNonLocked,
            isCredentialsNonExpired,
            isEnabled)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = userJdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRolesAsString());
            ps.setBoolean(6, user.isAccountNonExpired());
            ps.setBoolean(7, user.isAccountNonLocked());
            ps.setBoolean(8, user.isCredentialsNonExpired());
            ps.setBoolean(9, user.isEnabled());
            return ps;
        }, keyHolder);
        if (update == 1) {
            log.info("User with id: " + user.getUserId() + " is inserted.");
        }
        return String.valueOf(keyHolder.getKeyList().get(0).get("user_id"));
    }

    @Override
    public boolean deleteUser(UUID userId) {
        var sql = """
            DELETE FROM users
            WHERE user_id = ?;
            """;
        int delete = userJdbcTemplate.update(sql, userId);
        if (delete == 1) {
            log.info("User with id: " + userId + " is deleted.");
        }
        return delete == 1;
    }

}
