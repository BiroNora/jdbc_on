package com.norab.backstage.auth;

import com.norab.backstage.user.User;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<User> selectUserByName(String username);
}
