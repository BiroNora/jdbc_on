package com.norab.backstage.user;

import com.norab.utils.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface UserDao<User> {

    List<User> listUsers(Page page);

    Optional<User> selectUserById(UUID userId);

    List<User> selectUserByName(String name, boolean match);

    Optional<User> selectUserByName(String name);

    boolean updateUser(UUID userId, User user);

    String insertUser(User user) throws IllegalStateException;

    boolean deleteUser(UUID userId);
}
