package com.norab.role_photo;

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
class RolePhotoDataAccessRepositoryTest {
    @Autowired
    RolePhotoService service;
    @MockBean
    RolePhotoDataAccessRepository repository;

    @Test
    void selectRolePhotos() {
        when(repository.selectRolePhotos()).thenReturn(Stream
            .of(new RolePhoto(
                    101L, "http://kiskutya", 1L, 0L, 0L),
                new RolePhoto(
                    101L, "http://kiskutya", 1L, 0L, 0L)
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.selectRolePhotos().toString());
        verify(repository).selectRolePhotos();
        assertEquals(2, service.getRolePhotos().size());
    }

    @Test
    void selectRolePhotoByValidId() {
        Long id = 505L;
        when(repository.selectRolePhotoById(id)).thenReturn(
            Optional.of(new RolePhoto(
                101L, "http://101kiskutya", 1L, 0L, 0L)));

        assertNotNull(repository.selectRolePhotoById(id));
        verify(repository).selectRolePhotoById(id);
        assertEquals("http://101kiskutya", service.getPhoto(id).photoUrl());
    }

    @Test
    void selectRolePhotoByInvalidId() {
        Long id = 3L;
        when(repository.selectRolePhotoById(id)).thenReturn(null);

        assertNull(repository.selectRolePhotoById(id));
        verify(repository).selectRolePhotoById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getPhoto(id);
        });
    }

    @Test
    void insertRolePhoto() {
        RolePhoto photo = new RolePhoto(
            101L, "http://101kiskutya", 1L, 0L, 0L);
        when(repository.insertRolePhoto(photo)).thenReturn(1);

        assertEquals(1, repository.insertRolePhoto(photo));
        verify(repository).insertRolePhoto(photo);
        assertEquals(1, service.addNewRolePhoto(photo));
    }

    @Test
    void deleteRolePhoto() {
        Long id = 3L;
        repository.deleteRolePhoto(id);
        verify(repository, times(1)).deleteRolePhoto(id);
    }

    @Test
    void updateRolePhoto() {
        Long id = 101L;
        RolePhoto photo = new RolePhoto(
            101L, "http://101kiskutya", 1L, 0L, 0L);
        when(repository.updateRolePhoto(id, photo)).thenReturn(1);

        assertEquals(1, repository.updateRolePhoto(id, photo));
        verify(repository).updateRolePhoto(id, photo);
    }

}