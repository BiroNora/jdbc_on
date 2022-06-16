package com.norab.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

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

}