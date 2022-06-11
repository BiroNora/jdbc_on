package com.norab;

import com.norab.actor.Actor;
import com.norab.actor.ActorDataAccessRepository;
import com.norab.movie.Movie;
import com.norab.movie.MovieDataAccessRepository;
import com.norab.role.Plays;
import com.norab.role.RoleDataAccessRepository;
import com.norab.role_photo.RolePhoto;
import com.norab.role_photo.RolePhotoDataAccessRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
    private static ActorDataAccessRepository actorDao;
    private static MovieDataAccessRepository movieDao;
    private static RoleDataAccessRepository roleDao;
    private static RolePhotoDataAccessRepository rolePhotoDao;

    public DemoApplication(
        ActorDataAccessRepository actorDao, MovieDataAccessRepository movieDao, RoleDataAccessRepository roleDao, RolePhotoDataAccessRepository rolePhotoDao) {
        this.actorDao = actorDao;
        this.movieDao = movieDao;
        this.roleDao = roleDao;
        this.rolePhotoDao = rolePhotoDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

		/*System.out.println("\nFirstActor ----------------------------------------------\n");
		Optional<Actor> actor = actorDao.selectActors().stream().findFirst();
		System.out.println(actor);*/

        System.out.println("\nActors ----------------------------------------------\n");
        List<Actor> actors = actorDao.selectActors();
        actors.forEach(System.out::println);

        System.out.println("\nMovies ----------------------------------------------\n");
        List<Movie> movies = movieDao.selectMovies();
        movies.forEach(System.out::println);

        System.out.println("\nRoles -----------------------------------------------\n");
        List<Plays> roles = roleDao.selectRoles();
        roles.forEach(System.out::println);

        System.out.println("\nRolePhotos ------------------------------------------\n");
        List<RolePhoto> rolePhotos = rolePhotoDao.selectRolePhotos();
        rolePhotos.forEach(System.out::println);
    }

}
