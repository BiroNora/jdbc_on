package com.norab.photo;

import com.norab.actor.Person;
import com.norab.actor.ActorRepository;
import com.norab.exception.InvalidInputException;
import com.norab.movie.Movie;
import com.norab.movie.MovieRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    @Order(1)
    void selectPhotos() {
        List<Photo> photos = repository.selectPhotos();
        for (Photo p : photos) {
            System.out.print(p.getPhotoId() + ". ");
            System.out.println(p.getPhotoUrl());
        }
        assertEquals(photos.size(), 3);
        assertEquals(photos.get(2).getActorId(), 0);
    }

    @Test
    @Order(2)
    void insertPhoto() {
        Photo photo = new Photo("https://ZZZzzzzz", 1, null, null);

        int photoId = repository.insertPhoto(photo);
        Optional<Photo> photo1 = repository.selectPhotoById(photoId);
        assertTrue(photo1.isPresent());
        assertEquals(photo1.get().getMovieId(), 1);
        assertEquals(photo1.get().getPhotoUrl(), "https://ZZZzzzzz");
        assertEquals(photo1.get().getPhotoId(), 4);
    }

    @Test
    @Order(3)
    void insertPhotoByInvalidIds() {
        {
            Photo photo = new Photo("https://hun", 1024, null, null);

            assertThrows(InvalidInputException.class, () ->
                repository.insertPhoto(photo));
        }

        {
            Photo photo = new Photo(null, 1, null, null);

            assertThrows(InvalidInputException.class, () ->
                repository.insertPhoto(photo));
        }

        {
            Photo photo = new Photo("      ", 1, null, null);

            assertThrows(InvalidInputException.class, () ->
                repository.insertPhoto(photo));
        }
    }

    @Test
    @Order(4)
    void deletePhoto() {
        Integer photoId = 2;
        boolean result = repository.deletePhoto(photoId);
        assertEquals(true, result);

        Integer photoId1 = 2224;
        boolean result1 = repository.deletePhoto(photoId1);
        assertEquals(false, result1);
    }

    @Test
    @Order(5)
    void selectPhotoByValidId() {
        Integer photoId = 3;
        Optional<Photo> selected = repository.selectPhotoById(photoId);
        assertEquals(selected.orElseThrow().getPhotoUrl(), "https://dumbo");
    }

    @Test
    @Order(6)
    void selectPhotoByInvalidId() {
        Integer photoId = 278901;
        Optional<Photo> selected = repository.selectPhotoById(photoId);
        assertTrue(selected.isEmpty());
    }

    @Test
    @Order(7)
    void updatePhoto() {
        Photo pho = repository.selectPhotoById(3).orElseThrow();
        System.out.println(pho);
        pho.setPhotoUrl("http://pinocchio");
        pho.setMovieId(1);
        pho.setActorId(1);
        pho.setRoleId(null);
        System.out.println(pho);
        boolean result = repository.updatePhoto(3, pho);
        assertEquals(true, result);
        assertNotEquals(pho.getRoleId(), 0);
    }

    @Test
    @Order(8)
    void updatePhotoByInvalidIds() {
        Photo pho = repository.selectPhotoById(3).orElseThrow();
        System.out.println(pho);
        pho.setMovieId(11);
        pho.setActorId(1);
        pho.setRoleId(2);
        assertEquals(pho.getRoleId(), 2);

        System.out.println(pho);
        assertThrows(InvalidInputException.class, () ->
            repository.updatePhoto(3, pho));

        Photo pho1 = repository.selectPhotoById(3).orElseThrow();
        System.out.println(pho1);
        pho1.setMovieId(1);
        pho1.setActorId(11);
        pho1.setRoleId(2);
        System.out.println(pho1);
        assertThrows(InvalidInputException.class, () -> {
            repository.updatePhoto(3, pho1);
        });

        Photo pho2 = repository.selectPhotoById(3).orElseThrow();
        System.out.println(pho2);
        pho2.setMovieId(1);
        pho2.setActorId(1);
        pho2.setRoleId(22);
        System.out.println(pho2);
        assertThrows(InvalidInputException.class, () -> {
            repository.updatePhoto(3, pho2);
        });

        Photo pho3 = new Photo("https://pinokkio", 1, 1, 1);
        boolean result = repository.updatePhoto(22022, pho3);
        assertEquals(false, result);
    }

    @Test
    @Order(9)
    void doubleInsertPhoto() {
        String url = "https://CCC";
        Photo photoOrig = new Photo(url, 1, null, null);

        Integer photoId = repository.insertPhoto(photoOrig);
        repository.deletePhoto(photoId);
        Integer photoId1 = repository.insertPhoto(photoOrig);

        Optional<Photo> photo = repository.selectPhotoById(photoId);
        assertTrue(photo.isEmpty());

        Optional<Photo> photo1 = repository.selectPhotoById(photoId1);
        assertTrue(photo1.isPresent());
        assertEquals(photo1.get().getMovieId(), 1);
        assertEquals(photo1.get().getPhotoUrl(), url);

        List<Photo> photos = repository.selectPhotos();
        long count = photos.stream()
            .filter(x -> x.getPhotoUrl().equals(url))
            .count();
        assertEquals(1, count);
    }

    @Test
    @Order(10)
    void deleteReferredMovie() {
        Movie movie = new Movie("Kleo", "Patra", LocalDate.of(2002, Month.JULY, 22), true);
        Integer movieId = movieRepository.insertMovie(movie);

        Photo photo = new Photo("https://kleo", movieId, null, null);
        Integer photoId = repository.insertPhoto(photo);

        Integer del = movieRepository.deleteMovie(movieId);
        assertEquals(1, del);

        Optional<Photo> photo1 = repository.selectPhotoById(photoId);
        assertTrue(photo1.isPresent());
        assertEquals(0, photo1.get().getMovieId());
    }

    @Test
    @Order(11)
    void deleteReferredActor() {
        Person actor = new Person("Greta Garbo", LocalDate.of(2002, Month.JULY, 22));
        Integer actorId = actorRepository.insertActor(actor);

        Photo photo = new Photo("https://gretagarbo", null, Math.toIntExact(actorId), null);
        Integer photoId = repository.insertPhoto(photo);

        int del = actorRepository.deleteActor(Math.toIntExact(actorId));
        assertEquals(1, del);

        Optional<Photo> photo1 = repository.selectPhotoById(photoId);
        assertTrue(photo1.isPresent());
        assertEquals(0, photo1.get().getActorId());
    }

}