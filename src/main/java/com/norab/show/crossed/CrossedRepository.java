package com.norab.show.crossed;

import com.norab.show.actor.ActorRepository;
import com.norab.show.actor.ActorRowMapper;
import com.norab.show.actor.Person;
import com.norab.show.movie.Movie;
import com.norab.show.movie.MovieRowMapper;
import com.norab.utils.ResultResponse;
import com.norab.utils.Utils;
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

    @Override // +pagination
    public List<MoviesByActor> allMoviesByActorById(Integer id) {
        var sql = """
            SELECT role_name, title
            FROM movies
            JOIN
                (SELECT role_name, movie_id
                FROM plays
                WHERE actor_id = ?) AS p
            USING (movie_id)
            ;
            """;
        return jdbcTemplate.query(
            sql, (resultSet, i) -> new MoviesByActor(
                resultSet.getString("role_name"),
                resultSet.getString("title")), id);
    }

    @Override // +pagination
    public List<Movie> allMoviesByReleaseDateAsc() {
        var sql = """
            SELECT * FROM movies ORDER BY release_date DESC;
            """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override //*
    public List<Movie> searchByMovieTitle(String title, SearchLocation location) {
        String q = Utils.addPercent(title);
        var sql0 = """
            SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?);
            """;
        var sql1 = """
            SELECT * FROM movies WHERE LOWER(title_original) LIKE LOWER(?);
            """;
        var sql2 = """
            SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?) OR LOWER(title_original) like LOWER(?);
            """;
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

    @Override // +pagination
    public List<Person> searchByActorBirthDate(Short date) {
        var sql = """
            SELECT * FROM actors WHERE birth_date = ?;
            """;
        return jdbcTemplate.query(sql, new ActorRowMapper(), date);
    }

    @Override
    public List<ActorsByMovie> allActorsByMovie(String title) {
        String q = Utils.addPercent(title);
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
            ORDER BY full_name;
            """;
        return jdbcTemplate.query(sql, new ActorsByMovieMapper(), q, q);
    }

    @Override // +pagination
    public List<ResultResponse> allPlaysByActor(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT DISTINCT role_name FROM plays join
                (SELECT full_name, actor_id FROM actors
                WHERE LOWER(full_name) like LOWER(?)) AS a
            USING(actor_id);
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("role_name")), q);
    }

    @Override // +pagination
    public List<AllMoviesByActor> allMoviesByActorByName(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT title, title_original FROM movies
            JOIN
                ((SELECT actor_id, movie_id FROM plays) AS p
                JOIN
                    (SELECT full_name, actor_id FROM actors
                    WHERE LOWER(full_name) LIKE LOWER(?)) AS a
                USING(actor_id))
            USING(movie_id);
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new AllMoviesByActor(
            resultSet.getString("title"),
            resultSet.getString("title_original")), q);
    }

    @Override // +pagination
    public List<AllMoviesAndPlaysByActor> allMoviesAndPlaysByActor(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT title, title_original, role_name FROM movies
            JOIN
            ((SELECT actor_id, movie_id, role_name FROM plays) AS p
            JOIN
            (SELECT full_name, actor_id FROM actors
            WHERE LOWER(full_name) LIKE LOWER(?)) AS a
            USING(actor_id))
            USING(movie_id)
            ;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new AllMoviesAndPlaysByActor(
            resultSet.getString("title"),
            resultSet.getString("title_original"),
            resultSet.getString("role_name")), q);
    }

    @Override
    public List<AllPlaysAndActorsByMovie> allPlaysAndActorsByMovie(String movieTitle, SearchLocation location) {
        String q = Utils.addPercent(movieTitle);
        var sql0 = """
            SELECT DISTINCT role_name, full_name FROM actors
            JOIN
                ((SELECT movie_id, role_name, actor_id FROM plays) AS p
                JOIN
                    (SELECT movie_id FROM movies
                    WHERE LOWER(title) LIKE LOWER(?)) AS m
                USING (movie_id))
            USING (actor_id);
            """;
        var sql1 = """
            SELECT DISTINCT role_name, full_name FROM actors
            JOIN
                ((SELECT movie_id, role_name, actor_id FROM plays) AS p
                JOIN
                    (SELECT movie_id FROM movies
                    WHERE WHERE LOWER(title_original) LIKE LOWER(?)) AS m
                USING (movie_id))
            USING (actor_id);
            """;
        var sql2 = """
            SELECT DISTINCT role_name, full_name FROM actors
            JOIN
                ((SELECT movie_id, role_name, actor_id FROM plays) AS p
                JOIN
                    (SELECT movie_id FROM movies
                    WHERE LOWER(title) LIKE LOWER(?) OR LOWER(title_original) LIKE LOWER(?)) AS m
                USING (movie_id))
            USING (actor_id);
            """;
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(sql0, (resultSet, i) -> new AllPlaysAndActorsByMovie(
                    resultSet.getString("role_name"),
                    resultSet.getString("full_name")), q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(sql1, (resultSet, i) -> new AllPlaysAndActorsByMovie(
                    resultSet.getString("role_name"),
                    resultSet.getString("full_name")), q);
            }
            default -> {
                return jdbcTemplate.query(sql2, (resultSet, i) -> new AllPlaysAndActorsByMovie(
                    resultSet.getString("role_name"),
                    resultSet.getString("full_name")), q, q);
            }
        }
    }

    @Override
    public List<ResultResponse> allPhotosByActor(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT url FROM photos
            JOIN
            (SELECT actor_id FROM actors
            WHERE LOWER(full_name) LIKE LOWER(?)) AS a
            USING (actor_id);
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("url")), q);
    }

    @Override
    public List<ResultResponse> allPhotosByPlays(String roleName) {
        String q = Utils.addPercent(roleName);
        var sql = """
            SELECT url FROM photos
            JOIN
                (SELECT role_id FROM plays
                WHERE LOWER(role_name) LIKE LOWER(?)) AS p
            USING(role_id);                     
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("url")), q);
    }

    @Override
    public List<ResultResponse> allPhotosByMovie(String movieTitle, SearchLocation location) {
        String q = Utils.addPercent(movieTitle);
        var sql0 = """
            SELECT url FROM photos
            JOIN
               (SELECT movie_id FROM movies
               WHERE LOWER(title) LIKE LOWER(?)) AS p
            USING(movie_id);                
            """;
        var sql1 = """
            SELECT url FROM photos
            JOIN
               (SELECT movie_id FROM movies
               WHERE LOWER(title_original) LIKE LOWER(?)) AS p
            USING(movie_id);                 
            """;
        var sql2 = """
            SELECT url FROM photos
            JOIN
               (SELECT movie_id FROM movies
               WHERE LOWER(title) LIKE LOWER(?) OR LOWER(title_original) LIKE LOWER(?)) AS p
            USING(movie_id);                 
            """;
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(sql0, (resultSet, i) -> new ResultResponse(
                    resultSet.getString("url")), q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(sql1, (resultSet, i) -> new ResultResponse(
                    resultSet.getString("url")), q);
            }
            default -> {
                return jdbcTemplate.query(sql2, (resultSet, i) -> new ResultResponse(
                    resultSet.getString("url")), q, q);
            }
        }
    }

    @Override
    public List<MovieSpecs> movieSpecification(String movieTitle, SearchLocation location) {
        String q = Utils.addPercent(movieTitle);
        var sql0 = """
            SELECT title, title_original, p.role_name, actor_name, director_name, genres FROM movies
            JOIN
                (SELECT STRING_AGG(genre, '|') as genres, movie_id FROM genre GROUP BY movie_id) as g USING(movie_id)
            JOIN
                (SELECT role_name, actor_id, movie_id FROM plays) as p USING(movie_id)
            JOIN
                (SELECT STRING_AGG(full_name, '|') as director_name, movie_id FROM directors
                    JOIN
                        (SELECT full_name, actor_id FROM actors) AS dn USING(actor_id)
                    GROUP BY movie_id
                ) as d USING(movie_id)
            JOIN
                (SELECT full_name as actor_name, actor_id FROM actors) AS an ON an.actor_id = p.actor_id
            WHERE LOWER(title) LIKE LOWER(?)
            ;
            """;
        var sql1 = """
            SELECT title, title_original, p.role_name, actor_name, director_name, genres FROM movies
            JOIN
                (SELECT STRING_AGG(genre, '|') as genres, movie_id FROM genre GROUP BY movie_id) as g USING(movie_id)
            JOIN
                (SELECT role_name, actor_id, movie_id FROM plays) as p USING(movie_id)
            JOIN
                (SELECT STRING_AGG(full_name, '|') as director_name, movie_id FROM directors
                    JOIN
                        (SELECT full_name, actor_id FROM actors) AS dn USING(actor_id)
                    GROUP BY movie_id
                ) as d USING(movie_id)
            JOIN
                (SELECT full_name as actor_name, actor_id FROM actors) AS an ON an.actor_id = p.actor_id
            WHERE LOWER(title_original) LIKE LOWER(?)
            ;
            """;
        var sql2 = """
            SELECT title, title_original, p.role_name, actor_name, director_name, genres FROM movies
            JOIN
                (SELECT STRING_AGG(genre, '|') as genres, movie_id FROM genre GROUP BY movie_id) as g USING(movie_id)
            JOIN
                (SELECT role_name, actor_id, movie_id FROM plays) as p USING(movie_id)
            JOIN
                (SELECT STRING_AGG(full_name, '|') as director_name, movie_id FROM directors
                    JOIN
                        (SELECT full_name, actor_id FROM actors) AS dn USING(actor_id)
                    GROUP BY movie_id
                ) as d USING(movie_id)
            JOIN
                (SELECT full_name as actor_name, actor_id FROM actors) AS an ON an.actor_id = p.actor_id
            WHERE LOWER(title) LIKE LOWER(?) OR LOWER(title_original) LIKE LOWER(?)
            ;
            """;
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(sql0, (resultSet, i) -> {
                    String[] actorNames = resultSet.getString("actor_name").split("\\|");
                    String[] directorNames = resultSet.getString("director_name").split("\\|");
                    String[] genres = resultSet.getString("genres").split("\\|");
                    return new MovieSpecs(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getString("role_name"),
                        List.of(actorNames),
                        List.of(directorNames),
                        List.of(genres));
                }, q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(sql1, (resultSet, i) -> {
                    String[] actorNames = resultSet.getString("actor_name").split("\\|");
                    String[] directorNames = resultSet.getString("director_name").split("\\|");
                    String[] genres = resultSet.getString("genres").split("\\|");
                    return new MovieSpecs(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getString("role_name"),
                        List.of(actorNames),
                        List.of(directorNames),
                        List.of(genres));
                }, q);
            }
            default -> {
                return jdbcTemplate.query(sql2, (resultSet, i) -> {
                    String[] actorNames = resultSet.getString("actor_name").split("\\|");
                    String[] directorNames = resultSet.getString("director_name").split("\\|");
                    String[] genres = resultSet.getString("genres").split("\\|");
                    return new MovieSpecs(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getString("role_name"),
                        List.of(actorNames),
                        List.of(directorNames),
                        List.of(genres));
                }, q, q);
            }
        }
    }

    @Override
    public List<GenreActor> genresPerActor(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT full_name, STRING_AGG(genre, '|') as genres FROM
            (SELECT DISTINCT genre, full_name FROM actors
            JOIN
            (SELECT actor_id, movie_id FROM plays) AS p USING (actor_id)
            JOIN
            (SELECT movie_id, genre FROM genre) as g
                        ON g.movie_id = p.movie_id
            WHERE LOWER(full_name) LIKE LOWER(?)
            ORDER BY full_name, genre) AS d
            GROUP BY full_name
            ;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String[] genres = resultSet.getString("genres").split("\\|");
            return new GenreActor(
                resultSet.getString("full_name"),
                List.of(genres));
        }, q);
    }

    @Override
    public List<GenreActor> genresPerDirector(String actorName) {
        String q = Utils.addPercent(actorName);
        var sql = """
            SELECT full_name, STRING_AGG(DISTINCT genre, '|') as genres FROM genre
             JOIN   
                 (SELECT actors.actor_id, full_name, movie_id FROM actors
                 JOIN
                     (SELECT actor_id, movie_id FROM  directors) AS d
                     ON d.actor_id = actors.actor_id
                 WHERE LOWER(full_name) LIKE LOWER(?)) AS dir
             USING (movie_id)
             GROUP BY full_name
             ;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String[] genres = resultSet.getString("genres").split("\\|");
            return new GenreActor(
                resultSet.getString("full_name"),
                List.of(genres));
        }, q);
    }
}
