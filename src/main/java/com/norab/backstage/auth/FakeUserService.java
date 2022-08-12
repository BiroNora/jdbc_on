package com.norab.backstage.auth;

import com.google.common.collect.Lists;
import com.norab.backstage.user.User;
import com.norab.backstage.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.norab.security.Roles.*;

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
        List<User> users = Lists.newArrayList(
            new User(
                "user",
                passwordEncoder.encode("1234"),
                    List.of("user"),
                true,
                true,
                true,
                true
            ),
            new User(
                "staff",
                passwordEncoder.encode("1234"),
                    List.of("staff"),
                true,
                true,
                true,
                true
            ),
            new User(
                "hr",
                passwordEncoder.encode("1234"),
                    List.of("hr"),
                true,
                true,
                true,
                true
            )
        );
        return users;
    }
}
