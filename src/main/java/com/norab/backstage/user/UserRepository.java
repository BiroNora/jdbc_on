package com.norab.backstage.user;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements UserDao<User> {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listUsers(Page page) {
        return null;
    }

    @Override
    public int insertUser(User user) throws IllegalStateException {
        return 0;
    }

    @Override
    public DeleteResult deleteUser(UUID userId, boolean force) {
        return null;
    }

    @Override
    public Optional<User> selectUserById(UUID userId) {
        return Optional.empty();
    }

    @Override
    public List<User> selectUserByName(String name, boolean match) {
        return null;
    }

    @Override
    public int updateUser(UUID userId, User user) {
        return 0;
    }
}
