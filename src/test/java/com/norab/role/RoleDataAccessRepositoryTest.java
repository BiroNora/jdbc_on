package com.norab.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoleDataAccessRepositoryTest {
    @Autowired
    RoleService service;
    @MockBean
    RoleDataAccessRepository repository;

    @Test
    void selectRoles() {
        when(repository.selectRoles()).thenReturn(Stream
            .of(new Plays(33L, "Jack Sparrow", 2L, 3L),
                new Plays(302L, "Melvin Udall", 5L, 5L)
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.selectRoles().toString());
        verify(repository).selectRoles();
        assertEquals(2, service.getRoles().size());
    }

    @Test
    void selectRoleByValidId() {
        Long id = 202L;
        when(repository.selectRoleById(id)).thenReturn(
            Optional.of(new Plays(33L, "Jack Sparrow", 2L, 3L)));

        assertNotNull(repository.selectRoleById(id));
        verify(repository).selectRoleById(id);
        assertEquals("Jack Sparrow", service.getRole(id).roleName());
    }

    @Test
    void selectRoleByInvalidId() {
        Long id = 2022L;
        when(repository.selectRoleById(id)).thenReturn(null);

        assertNull(repository.selectRoleById(id));
        verify(repository).selectRoleById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getRole(id);
        });
    }

    @Test
    void insertRole() {
        Plays role = new Plays(33L, "Jack Sparrow", 2L, 3L);
        when(repository.insertRole(role)).thenReturn(1);

        assertEquals(1, repository.insertRole(role));
        verify(repository).insertRole(role);
        assertEquals(1, service.addNewRole(role));
    }

    @Test
    void deleteRole() {
        Long id = 3L;
        repository.deleteRole(id);
        verify(repository, times(1)).deleteRole(id);
    }

    @Test
    void updateRole() {
        Long id = 3L;
        Plays role = new Plays(33L, "Jack Sparrow", 2L, 3L);
        when(repository.updateRole(id, role)).thenReturn(1);

        assertEquals(1, repository.updateRole(id, role));
        verify(repository).updateRole(id, role);
    }

}