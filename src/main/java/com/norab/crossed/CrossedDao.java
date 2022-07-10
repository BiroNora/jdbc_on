package com.norab.crossed;

import com.norab.actor.Person;
import com.norab.movie.Movie;
import com.norab.photo.Photo;
import com.norab.role.Plays;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrossedDao {
    List<MoviesByActor> allMoviesByActor(Integer id);

    List<Person> searchByActorBirthDate(String date);

    List<Movie> allMoviesByReleaseDateAsc();

    List<Movie> searchByMovieTitle(String title, SearchLocation location);

    List<Plays> allPlaysByActor(Integer id);

    List<ActorsByFilm> allActorsByFilm(String title);

    List<Person> allActorsByAbcOrderAsc();

    List<Plays> allPlaysByFilm(Integer id);

    List<Photo> allPhotosByActor(Integer id);

    List<Photo> allPhotosByFilm(Integer id);

    List<Photo> allPhotosByPlays(Integer id);

    List<Photo> allPhotosByMovie(Integer id);

    public record MoviesByActor(
        String roleName,
        String title
    ) {
    }
}
