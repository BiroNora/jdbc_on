package com.norab.admin.user;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface UserDao<User> {

    List<User> listAllUsers(Page page);

    List<User> listAdmins(Page page);

    List<User> listRegisteredUsers(Page page);

    int insertUser(User user) throws IllegalStateException;

    DeleteResult deleteUser(UUID userId, boolean force);

    Optional<User> selectUserById(UUID userId);

    List<User> selectUserByName(String name, boolean match);

    int updateUser(UUID userId, User user);
}
