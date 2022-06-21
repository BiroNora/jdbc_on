package com.norab.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Test
    void selectRoles() {
    }

    @Test
    void insertRole() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void selectRoleByValidId() {
        Plays plays = new Plays("Sheryl Hoover", 2L, null);
        try {
            long id = repository.insertRole(plays);
            var plays1 = repository.selectRoleById(id);
            assertTrue(plays1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Plays> expected = repository.selectRoles();

        assertNotNull(expected);
        for (Plays p : expected) {
            System.out.println(p.getId());
            System.out.println(p.getRoleName());
        }
        assertTrue(expected.size() != 0);
    }

    @Test
    void selectRoleByInvalidId() {
        var plays = repository.selectRoleById(1024L);
        assertTrue(plays.isEmpty());
    }

    @Test
    void updateRole() {
    }
}