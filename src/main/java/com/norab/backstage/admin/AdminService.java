package com.norab.backstage.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminService implements UserDetailsService {
    private final AdminDao adminDao;

    @Autowired
    public AdminService(@Qualifier("adminJdbcTemplate") AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) adminDao
            .adminByName(username);
        //new UsernameNotFoundException(String.format("Username %s not found", username));
    }
}
