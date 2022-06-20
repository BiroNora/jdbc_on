package com.norab.photo;

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
class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository repository;

    @Test
    @Order(1)
    void selectPhotos() {
        List<Photo> photos = repository.selectPhotos();
        for (Photo p : photos) {
            System.out.println(p.getId());
            System.out.println(p.getPhotoUrl());
        }
        assertEquals(photos.size(), 3);
        assertEquals(photos.get(2).getActorId(), 0);
    }

    @Test
    @Order(2)
    void insertPhoto() {
        Photo photo = new Photo("https://ZZZzzzzz", 1L, null, null);

        long id = repository.insertPhoto(photo);
        Optional<Photo> photo1 = repository.selectPhotoById(id);
        assertTrue(photo1.isPresent());
        assertEquals(photo1.get().getMovieId(), 1);
        assertEquals(photo1.get().getPhotoUrl(), "https://ZZZzzzzz");
        assertEquals(photo1.get().getId(), 4);
    }

    @Test
    @Order(3)
    void deletePhoto() {
        Long id = 2L;
        int result = repository.deletePhoto(id);
        assertEquals(1, result);

        Long id1 = 2224L;
        int result1 = repository.deletePhoto(id1);
        assertEquals(0, result1);
    }

    @Test
    @Order(4)
    void selectPhotoById() {
        Long id = 3L;
        Optional<Photo> selected = repository.selectPhotoById(id);
        assertEquals(selected.get().getPhotoUrl(), "https://dumbo");

        Long id1 = 278901L;
        Optional<Photo> selected1 = repository.selectPhotoById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(5)
    void updatePhoto() {
        Photo pho = repository.selectPhotoById(3L).get();
        System.out.println(pho);
        pho.setMovieId(0L);
        pho.setActorId(0L);
        pho.setRoleId(2L);
        System.out.println(pho);
        int result = repository.updatePhoto(3L, pho);
        assertEquals(1, result);
        assertNotEquals(pho.getRoleId(), 0);

        Photo pho1 = new Photo("https://pinokkio", 1L, 1L, 1L);
        int result1 = repository.updatePhoto(22022L, pho1);
        assertEquals(0, result1);
    }
}