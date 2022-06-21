package com.norab.role;

import com.norab.exception.InvalidInputException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Test
    @Order(1)
    void selectRoles() {
        List<Plays> roles = repository.selectRoles();
        for (Plays p : roles) {
            System.out.print(p.getId() + ". ");
            System.out.println(p.getRoleName());
        }
        assertEquals(roles.size(), 3);
        assertEquals(roles.get(1).getMovieId(), 2);
    }

    @Test
    @Order(2)
    void insertRole() {
        Plays plays = new Plays("Sheryl Hoover", 2L, null);

        long id = repository.insertRole(plays);
        Optional<Plays> role1 = repository.selectRoleById(id);
        assertTrue(role1.isPresent());
        assertEquals(role1.get().getRoleName(), "Sheryl Hoover");
        assertEquals(role1.get().getMovieId(), 2);
        assertEquals(role1.get().getActorId(), 0);
    }

    @Test
    @Order(3)
    void deleteRole() {
        Long id = 2L;
        int result = repository.deleteRole(id);
        assertEquals(1, result);

        Long id1 = 772L;
        int result1 = repository.deleteRole(id1);
        assertEquals(0, result1);

    }

    @Test
    @Order(3)
    void selectRoleByValidId() {
        Plays plays = new Plays("Olive Hoover", 2L, null);
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
    @Order(4)
    void selectRoleByValidId1() {
        Long id = 3L;
        Optional<Plays> selected = repository.selectRoleById(id);
        assertEquals(selected.orElseThrow().getRoleName(), "Jimmy McGinty");
    }

    @Test
    @Order(5)
    void selectRoleByInvalidId() {
        var plays = repository.selectRoleById(1024L);
        assertTrue(plays.isEmpty());
    }

    @Test
    @Order(6)
    void updateRole() {
        Plays role = repository.selectRoleById(3L).orElseThrow();
        System.out.println(role);
        role.setRoleName("Jimi Hendrix");
        role.setMovieId(2L);
        role.setActorId(3L);
        System.out.println(role);
        int result = repository.updateRole(3L, role);
        assertEquals(1, result);
        assertNotEquals(role.getRoleName(), "Jimmy McGinty");
    }

    @Test
    @Order(7)
    void updateRoleByInvalidIds() {
        Plays role = repository.selectRoleById(3L).orElseThrow();
        System.out.println(role);
        role.setMovieId(202L);
        role.setActorId(3L);
        assertEquals(role.getActorId(), 3L);

        System.out.println(role);
        assertThrows(InvalidInputException.class, () -> {
             repository.updateRole(3L, role);
            });

        Plays role1 = repository.selectRoleById(3L).orElseThrow();
        System.out.println(role1);
        role1.setMovieId(1L);
        role1.setActorId(333L);
        System.out.println(role1);
        assertThrows(InvalidInputException.class, () -> {
                repository.updateRole(3L, role1);
            });

        Plays role3 = new Plays("Paul Vitti", 2L, null);
        int result = repository.updateRole(255587L, role3);
        assertEquals(0, result);
    }
}