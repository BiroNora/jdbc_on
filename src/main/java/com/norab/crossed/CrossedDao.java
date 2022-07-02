package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.movie.Movie;
import com.norab.photo.Photo;
import com.norab.role.Plays;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrossedDao {
    List<Movie> allMoviesByActor(Long id);
    List<Actor> selectActorByBirthDate(String date);
    List<Movie> allMoviesByReleaseDateAsc();
    List<Plays> allPlaysByActor(Long id);
    List<Actor> allActorsByFilm(Long id);
    List<Actor> allActorsByAbcOrderAsc();
    List<Plays> allPlaysByFilm(Long id);
    List<Photo> allPhotosByActor(Long id);
    List<Photo> allPhotosByFilm(Long id);
    List<Photo> allPhotosByPlays(Long id);
    List<Photo> allPhotosByMovie(Long id);
}
