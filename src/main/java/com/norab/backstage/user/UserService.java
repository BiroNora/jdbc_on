package com.norab.backstage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userRepository")
    private final UserDao<User> userDao;

    public UserService(@Qualifier("userRepository") UserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userDao
            .selectUserByName(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
