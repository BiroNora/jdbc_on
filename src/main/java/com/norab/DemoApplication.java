package com.norab;

import com.norab.actor.*;
import com.norab.movie.Movie;
import com.norab.movie.MovieDao;
import com.norab.role.Plays;
import com.norab.role.RoleDao;
import com.norab.role_photo.RolePhoto;
import com.norab.role_photo.RolePhotoDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	private static ActorDao<Actor> actorDao;
	private static MovieDao<Movie> movieDao;
	private static RoleDao<Plays> roleDao;
	private static RolePhotoDao<RolePhoto> rolePhotoDao;

	public DemoApplication(
		ActorDao<Actor> actorDao, MovieDao<Movie> movieDao, RoleDao<Plays> roleDao, RolePhotoDao<RolePhoto> rolePhotoDao) {
		DemoApplication.actorDao = actorDao;
		DemoApplication.movieDao = movieDao;
		DemoApplication.roleDao = roleDao;
		DemoApplication.rolePhotoDao = rolePhotoDao;
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
