package com.norab.crossed;

import com.norab.actor.ActorRepository;
import com.norab.movie.Movie;
import com.norab.movie.MovieRowMapper;
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
}
