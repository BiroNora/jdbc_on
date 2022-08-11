package com.norab.backstage.user;

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
public class UserRepository implements UserDao<User> {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);
    @Autowired
    private final JdbcTemplate userJdbcTemplate;

    public UserRepository(@Qualifier("userJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.userJdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listUsers(Page page) {
        var sql = "SELECT user_id, full_name, email, password, phone " +
            "FROM users ORDER BY full_name LIMIT '" + page.getLimit() + "' " +
            "OFFSET '" + page.getOffset() + "'";
        return userJdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> selectUserById(UUID userId) {
        var sql = """
            SELECT user_id, full_name, email, password, phone
            FROM users WHERE user_id = ?;
            """;
        return userJdbcTemplate.query(sql, new UserRowMapper(), userId)
            .stream()
            .findFirst();
    }

    @Override
    public List<User> selectUserByName(String name, boolean match) {
        var sql = """
            SELECT user_id, full_name, email, password, phone
            FROM users
            WHERE LOWER(full_name) LIKE LOWER(?);
            """;
        String q = match ? Utils.addPercent(name) : name;
        return userJdbcTemplate.query(sql, new UserRowMapper(), q);
    }

    @Override
    public User userByName(String name) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public int updateUser(UUID userId, User user) {
        var sql = """
            UPDATE users
            SET full_name = ?, email = ?, password = ?, phone = ?
            WHERE user_id = ?;
            """;
        int update = userJdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getUserId());
        if (update == 1) {
            log.info("User with id: " + userId + " is updated.");
        }
        return update;
    }

    @Override
    public String insertUser(User user) throws IllegalStateException {
        var sql = """
            INSERT INTO users(full_name, email, password, phone) VALUES(?, ?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = userJdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            return ps;
        }, keyHolder);
        if (update == 1) {
            log.info("User with id: " + user.getUserId() + " is inserted.");
        }
        return String.valueOf(keyHolder.getKeyList().get(0).get("user_id"));
    }

    @Override
    public int deleteUser(UUID userId) {
        var sql = """
            DELETE FROM users
            WHERE user_id = ?;
            """;
        int delete = userJdbcTemplate.update(sql, userId);
        if (delete == 1) {
            log.info("Role with id: " + userId + " is deleted.");
        }
        return delete;
    }

}
