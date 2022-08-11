package com.norab.backstage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("userJdbcTemplate") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userDAO
            .userByName(username);
        //new UsernameNotFoundException(String.format("Username %s not found", username));
    }
}
