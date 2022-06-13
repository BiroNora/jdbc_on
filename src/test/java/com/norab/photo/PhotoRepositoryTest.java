package com.norab.photo;

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
class PhotoRepositoryTest {
    @Autowired
    PhotoService service;
    @MockBean
    PhotoRepository repository;

    @Test
    void selectRolePhotos() {
        when(repository.selectPhotos()).thenReturn(Stream
            .of(new Photo(
                    101L, "http://101kiskutya", 1L, 0L, 0L),
                new Photo(
                    101L, "http://101kiskutya", 1L, 0L, 0L)
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.selectPhotos().toString());
        verify(repository).selectPhotos();
        assertEquals(2, service.getPhotos().size());
    }

    @Test
    void selectRolePhotoByValidId() {
        Long id = 505L;
        when(repository.selectPhotoById(id)).thenReturn(
            Optional.of(new Photo(
                101L, "http://101kiskutya", 1L, 0L, 0L)));

        assertNotNull(repository.selectPhotoById(id));
        verify(repository).selectPhotoById(id);
        assertEquals("http://101kiskutya", service.getPhoto(id).photoUrl());
    }

    @Test
    void selectRolePhotoByInvalidId() {
        Long id = 3L;
        when(repository.selectPhotoById(id)).thenReturn(null);

        assertNull(repository.selectPhotoById(id));
        verify(repository).selectPhotoById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getPhoto(id);
        });
    }

    @Test
    void insertRolePhoto() {
        Photo photo = new Photo(
            101L, "http://101kiskutya", 1L, 0L, 0L);
        when(repository.insertPhoto(photo)).thenReturn(1);

        assertEquals(1, repository.insertPhoto(photo));
        verify(repository).insertPhoto(photo);
        assertEquals(1, service.addNewPhoto(photo));
    }

    @Test
    void deleteRolePhoto() {
        Long id = 3L;
        repository.deletePhoto(id);
        verify(repository, times(1)).deletePhoto(id);
    }

    @Test
    void updateRolePhoto() {
        Long id = 101L;
        Photo photo = new Photo(
            101L, "http://101kiskutya", 1L, 0L, 0L);
        when(repository.updatePhoto(id, photo)).thenReturn(1);

        assertEquals(1, repository.updatePhoto(id, photo));
        verify(repository).updatePhoto(id, photo);
    }

}