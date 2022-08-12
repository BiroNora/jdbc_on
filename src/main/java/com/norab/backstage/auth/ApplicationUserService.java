package com.norab.backstage.auth;

import com.norab.backstage.user.User;
import com.norab.backstage.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("csiko")
public class ApplicationUserService implements ApplicationUserDao {
    @Autowired
    UserDao<User> userDao;

    @Override
    public Optional<User> selectUserByName(String username) {
        try {
            return Optional.of(userDao.userByName(username));
        } catch(UsernameNotFoundException e) {
            return Optional.empty();
        }
    }
}
