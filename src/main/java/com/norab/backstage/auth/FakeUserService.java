package com.norab.backstage.auth;

import com.google.common.collect.Lists;
import com.norab.backstage.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeUserService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByName(String username) {
        return getUsers().stream()
            .filter(user -> username.equals(user.getUsername()))
            .findFirst();
    }

    private List<User> getUsers() {
        return Lists.newArrayList(
            new User(
                "user@gmail.com",
                passwordEncoder.encode("1234"),
                "user"
            ),
            new User(
                "staff@movie.com",
                passwordEncoder.encode("1234"),
                "staff"
            ),
            new User(
                "hr@movie.com",
                passwordEncoder.encode("1234"),
                "hr"
            )
        );
    }
}
