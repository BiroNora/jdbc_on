package com.norab.show.crossed;

import com.norab.show.actor.Person;
import com.norab.show.movie.Movie;
import com.norab.utils.ResultResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrossedDao {
    List<MoviesByActor> allMoviesByActorById(Integer id);

    List<Movie> allMoviesByReleaseDateAsc();

    List<Movie> searchByMovieTitle(String title, SearchLocation location);

    List<Person> searchByActorBirthDate(Short date);

    List<ActorsByMovie> allActorsByMovie(String title);

    List<ResultResponse> allPlaysByActor(String actorName);

    List<AllMoviesByActor> allMoviesByActorByName(String actorName);

    List<AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(String actorName);

    List<AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(String movieTitle, SearchLocation location);

    List<ResultResponse> allPhotosByActor(String actorName);

    List<ResultResponse> allPhotosByPlays(String roleName);

    List<ResultResponse> allPhotosByMovie(String movieTitle, SearchLocation location);

    List<MovieSpecs> movieSpecification(String movieTitle, SearchLocation location);

    List<GenreActor> genresPerActor(String actorName);

    List<GenreActor> genresPerDirector(String actorName);

    record MoviesByActor(
        String roleName,
        String title
    ) {
    }

    record AllMoviesByActor(
        String title,
        String titleOriginal
    ) {
    }

    record AllMoviesAndPlaysByActor(
        String title,
        String titleOriginal,
        String roleName
    ) {
    }

    record AllPlaysAndActorsByMovie(
        String roleName,
        String fullName
    ) {
    }

    record MovieSpecs(
        String title,
        String titleOriginal,
        String roleName,
        List<String> actorName,
        List<String> directorName,
        List<String> genres
    ) {
    }

    record GenreActor(
        String fullName,
        List<String> genres
    ) {
    }
}
