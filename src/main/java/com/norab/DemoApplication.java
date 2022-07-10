package com.norab;

import com.norab.actor.Person;
import com.norab.actor.ActorRepository;
import com.norab.movie.Movie;
import com.norab.movie.MovieRepository;
import com.norab.photo.Photo;
import com.norab.photo.PhotoRepository;
import com.norab.role.Plays;
import com.norab.role.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
    private static ActorRepository actorDao;
    private static MovieRepository movieDao;
    private static RoleRepository roleDao;
    private static PhotoRepository photoDao;

    public DemoApplication(
        ActorRepository actorDao, MovieRepository movieDao, RoleRepository roleDao, PhotoRepository photoDao) {
        this.actorDao = actorDao;
        this.movieDao = movieDao;
        this.roleDao = roleDao;
        this.photoDao = photoDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

		/*System.out.println("\nFirstActor ----------------------------------------------\n");
		Optional<Actor> actor = actorDao.selectActors().stream().findFirst();
		System.out.println(actor);*/

        System.out.println("\nActors ----------------------------------------------\n");
        List<Person> actors = actorDao.selectActors();
        actors.forEach(System.out::println);

        System.out.println("\nMovies ----------------------------------------------\n");
        List<Movie> movies = movieDao.selectMovies();
        movies.forEach(System.out::println);

        System.out.println("\nRoles -----------------------------------------------\n");
        List<Plays> roles = roleDao.selectRoles();
        roles.forEach(System.out::println);

        System.out.println("\nPhotos ----------------------------------------------\n");
        List<Photo> photos = photoDao.selectPhotos();
        photos.forEach(System.out::println);
    }

}
