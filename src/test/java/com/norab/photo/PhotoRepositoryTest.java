package com.norab.photo;

import com.norab.actor.ActorRepository;
import com.norab.actor.Person;
import com.norab.exception.InvalidInputException;
import com.norab.movie.Movie;
import com.norab.movie.MovieRepository;
import com.norab.utils.DeleteResult;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
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
    void selectPhotos() {
        List<Photo> photos = repository.selectPhotos();
        for (Photo p : photos) {
            System.out.print(p.getPhotoId() + ". ");
            System.out.println(p.getPhotoUrl());
        }
        assertTrue(photos.size() > 0);
    }

    @Test
    void insertPhoto() {
        String url = "https://ZZZzzzzz";
        Photo photo = new Photo(url, 1, null, null);

        int photoId = repository.insertPhoto(photo);
        Optional<Photo> photo1 = repository.selectPhotoById(photoId);
        assertTrue(photo1.isPresent());
        assertEquals(1, photo1.get().getMovieId());
        assertEquals(url, photo1.get().getPhotoUrl());
        assertEquals(photoId, photo1.get().getPhotoId());
        //TODO: create equals object
    }

    @Test
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
    void deletePhoto() {
        Integer photoId = 2;
        boolean result = repository.deletePhoto(photoId);
        assertEquals(true, result);

        Integer photoId1 = 2224;
        boolean result1 = repository.deletePhoto(photoId1);
        assertEquals(false, result1);
    }

    @Test
    void selectPhotoByValidId() {
        Integer photoId = 3;
        Optional<Photo> selected = repository.selectPhotoById(photoId);
        assertTrue(selected.isPresent());
        assertEquals("https://fictional.com/actor/images/3.jpg", selected.get().getPhotoUrl());

    }

    @Test
    void selectPhotoByInvalidId() {
        Integer photoId = 278901;
        Optional<Photo> selected = repository.selectPhotoById(photoId);
        assertTrue(selected.isEmpty());
    }

    @Test
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
    void updatePhotoByInvalidIds() {
        int photoId = 3;
        Photo pho = repository.selectPhotoById(photoId).orElseThrow();
        System.out.println(pho);
        pho.setMovieId(11);
        pho.setActorId(1);
        pho.setRoleId(2);
        assertEquals(pho.getRoleId(), 2);

        System.out.println(pho);
        assertThrows(InvalidInputException.class, () ->
            repository.updatePhoto(photoId, pho));

        Photo pho1 = repository.selectPhotoById(photoId).orElseThrow();
        System.out.println(pho1);
        pho1.setMovieId(1);
        pho1.setActorId(41);
        pho1.setRoleId(2);
        System.out.println(pho1);
        assertThrows(InvalidInputException.class, () -> {
            repository.updatePhoto(photoId, pho1);
        });

        Photo pho2 = repository.selectPhotoById(photoId).orElseThrow();
        System.out.println(pho2);
        pho2.setMovieId(1);
        pho2.setActorId(1);
        pho2.setRoleId(82);
        System.out.println(pho2);
        assertThrows(InvalidInputException.class, () -> {
            repository.updatePhoto(photoId, pho2);
        });

        Photo pho3 = new Photo("https://pinokkio", 1, 1, 1);
        boolean result = repository.updatePhoto(22022, pho3);
        assertEquals(false, result);
    }

    @Test
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
    void deleteReferredMovie() {
        Movie movie = new Movie("Kleo", "Patra", (short) 2002);
        Integer movieId = movieRepository.insertMovie(movie);

        Photo photo = new Photo("https://kleo", movieId, null, null);
        Integer photoId = repository.insertPhoto(photo);

        assertEquals(DeleteResult.SUCCESS, movieRepository.deleteMovie(movieId, false));

        Optional<Photo> photo1 = repository.selectPhotoById(photoId);
        assertTrue(photo1.isPresent());
        assertEquals(0, photo1.get().getMovieId());
    }

    @Test
    void deleteReferredActor() {
        Person actor = new Person("Greta Garbo", (short) 2002);
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