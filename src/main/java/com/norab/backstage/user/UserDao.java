package com.norab.backstage.user;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface UserDao<User> {

    List<User> listUsers(Page page);

    int insertUser(User user) throws IllegalStateException;

    DeleteResult deleteUser(UUID userId, boolean force);

    Optional<User> selectUserById(UUID userId);

    List<User> selectUserByName(String name, boolean match);

    int updateUser(UUID userId, User user);
}
