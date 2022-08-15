package com.norab.backstage.user;

import com.norab.security.Permissions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

class UserTest {

    @Test
    void getRoles() {
        User user = new User(null, "ZZTop", "zzt@gmail.com", "12345", "5557-222", "user", true, true, true, true);
        List<String> roles = user.getRoles();
        for (String r : roles) {
            System.out.println("Role: " + r);
        }
        assertEquals(1, roles.size());
        assertEquals("USER", roles.get(0));

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority g : authorities) {
            System.out.println("Authorities of this role: " + g);
        }
        assertEquals("show:read", authorities.stream().findFirst().get().getAuthority());
        assertEquals("ROLE_USER", authorities.stream().skip(1).findFirst().get().getAuthority());
        assertEquals("opinion:write", authorities.stream().skip(2).findFirst().get().getAuthority());
        assertEquals("opinion:delete", authorities.stream().skip(3).findFirst().get().getAuthority());
        assertEquals(4, authorities.size());
    }
}
