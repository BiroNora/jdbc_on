package com.norab.backstage.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private SimpleGrantedAuthority staff;
    private SimpleGrantedAuthority write;

    @BeforeEach
    void setUp() {
        user = new User("test@elek.hu", "Life is good!", "user, staff");
        write = new SimpleGrantedAuthority("opinion:write");
        staff = new SimpleGrantedAuthority("ROLE_STAFF");
    }

    @Test
    void setUpRoles() {
        {
            User u = new User("test@elek.hu", "Life if good!", "   user   , stAff");
            assertEquals("USER,STAFF", u.getRolesAsString());
        }
        assertThrows(IllegalArgumentException.class, () -> {
            new User("test@elek.hu", "Life if good!", "XXX");
        });
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertNotNull(authorities);
        assertTrue(authorities.size() > 2);
        assertTrue(authorities.contains(write));
        assertTrue(authorities.contains(staff));
    }

    @Test
    void getRoles() {
        List<String> roles = user.getRoles();
        assertNotNull(roles);
        assertEquals(2, roles.size());
        assertTrue(roles.contains("STAFF"));
    }

    @Test
    void getRolesAsString() {
        assertEquals("USER,STAFF", user.getRolesAsString());
    }

    @Test
    void getRolesMe() {
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
