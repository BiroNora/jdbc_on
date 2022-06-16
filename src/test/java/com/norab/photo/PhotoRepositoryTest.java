package com.norab.photo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository repository;

    @Test
    void selectPhotoByValidId() {
        Photo photo = new Photo("http://101kiskutya", 1L, null, null);
        try {
            long id = repository.insertPhoto(photo);
            var photo1 = repository.selectPhotoById(id);
            assertTrue(photo1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Photo> expected = repository.selectPhotos();

        assertNotNull(expected);
        for (Photo p : expected) {
            System.out.println(p.getId());
            System.out.println(p.getPhotoUrl());
        }
        assertTrue(expected.size() != 0);
    }

}