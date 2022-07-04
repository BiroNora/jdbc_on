package com.norab.crossed;

import com.norab.actor.Actor;
import com.norab.actor.ActorRepository;
import com.norab.actor.ActorRowMapper;
import com.norab.movie.Movie;
import com.norab.movie.MovieRowMapper;
import com.norab.photo.Photo;
import com.norab.role.Plays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrossedRepository implements CrossedDao {
    private static final Logger log = LoggerFactory.getLogger(ActorRepository.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CrossedRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> allMoviesByActor(Long id) {
        var sql = """
            SELECT role_name, movies.movie_id, title, title_original, release_date, movie_film
            FROM movies
            JOIN
            (SELECT role_name, movie_id
            FROM plays
            WHERE actor_id = ?) AS p
            USING (movie_id)
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), id);
    }

    @Override
    public List<Movie> allMoviesByReleaseDateAsc() {
        var sql = """
            SELECT * FROM movies ORDER BY release_date ASC;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public List<Movie> searchByMovieTitle(String title, SearchLocation location) {
        String q = title.strip();
        var sql0 = """
            SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?);
            """;
        var sql1 = """
            SELECT * FROM movies WHERE LOWER(title_original) LIKE LOWER(?);
            """;
        var sql2 = """
            SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?) OR LOWER(title_original) like LOWER(?);
            """;
        if (!q.startsWith("%")) {
            q = "%" + q;
        }
        if (!q.endsWith("%")) {
            q = q + "%";
        }
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(sql0, new MovieRowMapper(), q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(sql1, new MovieRowMapper(), q);
            }
            default -> {
                return jdbcTemplate.query(sql2, new MovieRowMapper(), q, q);
            }
        }
    }

    @Override
    public List<Actor> searchByActorBirthDate(String date) {
        var sql = """
            SELECT * FROM actors WHERE birth_date like ?;
            """;
        return jdbcTemplate.query(sql, new ActorRowMapper(), date);
    }

    @Override
    public List<ActorsByFilm> allActorsByFilm(String title) {
        String q = title.strip();
        if (!q.startsWith("%")) {
            q = "%" + q;
        }
        if (!q.endsWith("%")) {
            q = q + "%";
        }
        var sql = """
            SELECT pm.title AS title, pm.title_original AS title_original,
                   full_name, pm.role_name AS role_name
            FROM actors
            JOIN
            (SELECT actor_id, role_name, m.title, m.title_original
            FROM plays
            JOIN
            	(SELECT movie_id, title, title_original
            	FROM movies
            	WHERE LOWER(title) LIKE LOWER(?)
            	OR LOWER(title_original) LIKE LOWER(?)
            	) AS m
            USING (movie_id)) AS pm
            USING (actor_id)
            ORDER BY full_name;                                                                                          WHERE LOWER(m.title) LIKE LOWER('%Miss%') OR LOWER(m.title_original) like LOWER('%Miss%');
            """;
        return jdbcTemplate.query(sql, new ActorsByFilmMapper(), q, q);
    }

    @Override
    public List<Actor> allActorsByAbcOrderAsc() {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Plays> allPlaysByActor(Long id) {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Plays> allPlaysByFilm(Long id) {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Photo> allPhotosByActor(Long id) {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Photo> allPhotosByFilm(Long id) {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Photo> allPhotosByPlays(Long id) {
        var sql = """
                        
            """;
        return null;
    }

    @Override
    public List<Photo> allPhotosByMovie(Long id) {
        var sql = """
                        
            """;
        return null;
    }
}
