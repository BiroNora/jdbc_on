package com.norab.director;

import com.norab.crossed.SearchLocation;
import com.norab.exception.InvalidInputException;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepository implements DirectorDao<Director> {
    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public DirectorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ResultResponse> listDirectors(Page page) {
        var sql = """
            SELECT DISTINCT full_name AS directors FROM directors
                          JOIN
                          (SELECT full_name, actor_id FROM actors) AS dn USING(actor_id)
                          ORDER BY directors asc;
            ;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("directors")));
    }

    @Override
    public List<Directors> listDirectorsAndMovies() {
        var sql = """
            SELECT STRING_AGG(full_name, '|') AS director, title, title_original, release_date
             FROM movies
             JOIN
                 (SELECT movie_id, full_name FROM directors
                     JOIN
                         (SELECT actor_id, full_name FROM actors) AS act USING(actor_id)
                     ORDER BY full_name
                 ) as b
                 ON b.movie_id = movies.movie_id
             GROUP BY movies.movie_id
             ORDER BY director
             ;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String[] directors = resultSet.getString("director").split("\\|");
            return new Directors(
                List.of(directors),
                resultSet.getString("title"),
                resultSet.getString("title_original"),
                resultSet.getShort("release_date"));
        });
    }

    @Override
    public boolean insertDirector(Director director) {
        if (!director.isValid() || selectDirectorById(director.getActorId(), director.getMovieId())) {
            throw new InvalidInputException("Invalid director");
        }
        var sql = """
            INSERT into directors(actor_id, movie_id) VALUES (?, ?);
            """;
        try {
            int update = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, director.getActorId());
                ps.setInt(2, director.getMovieId());
                return ps;
            });

            log.info("New director inserted: " + director);
            return update == 1;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Illegal id");
        }
    }

    @Override
    public boolean deleteDirector(Integer actorId, Integer movieId) {
        var sql = """
            DELETE FROM directors
            WHERE actor_id = ? AND movie_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, actorId, movieId);
        if (delete == 1) {
            log.info(String.format("Director %d with movie %d is deleted.", actorId, movieId));
        }
        return delete == 1;
    }

    @Override
    public boolean selectDirectorById(Integer actorId, Integer movieId) {
        var sql = """
            SELECT actor_id, movie_id
            FROM directors
            WHERE actor_id = ? AND movie_id = ?;
            """;
        Optional<Director> selected = jdbcTemplate.query(sql, new DirectorRowMapper(), actorId, movieId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Director %d with movie %d is selected.", actorId, movieId));
        }
        return selected.isPresent();
    }

    @Override
    public List<MoviesByDirector> selectMoviesByDirector(String name) {
        String q = Utils.addPercent(name);
        var sql = """
            SELECT title, title_original, release_date, full_name
            FROM movies
            JOIN
            ((SELECT actor_id, movie_id
            FROM directors) AS dir
            JOIN
            (SELECT actor_id, full_name
                 FROM actors
                 WHERE LOWER(full_name) LIKE LOWER(?)) AS act
            USING(actor_id))
            USING(movie_id)
            ;
             """;
        return jdbcTemplate.query(
            sql, (resultSet, i) -> new MoviesByDirector(
                resultSet.getString("title"),
                resultSet.getString("title_original"),
                resultSet.getShort("release_date"),
                resultSet.getString("full_name")), q);
    }

    @Override
    public List<ResultResponse> selectDirectorsByMovieTitle(String title, SearchLocation location) {
        String q = Utils.addPercent(title);
        var sql0 = """
             SELECT full_name, title
             FROM actors AS a
             JOIN
                 ((SELECT actor_id, movie_id
                 FROM directors) AS d
                 JOIN
                     (SELECT movie_id, title, title_original
                     FROM movies) AS m
                     USING(movie_id))
                 USING(actor_id)
            WHERE LOWER(m.title) LIKE LOWER(?)
            ;
            """;
        var sql1 = """
             SELECT full_name, title
             FROM actors AS a
             JOIN
                 ((SELECT actor_id, movie_id
                 FROM directors) AS d
                 JOIN
                     (SELECT movie_id, title, title_original
                     FROM movies) AS m
                     USING(movie_id))
                 USING(actor_id)
            WHERE LOWER(m.title_original) LIKE LOWER(?)
            ;
            """;
        var sql2 = """
             SELECT full_name, title
             FROM actors AS a
             JOIN
                 ((SELECT actor_id, movie_id
                 FROM directors) AS d
                 JOIN
                     (SELECT movie_id, title, title_original
                     FROM movies) AS m
                     USING(movie_id))
                 USING(actor_id)
            WHERE LOWER(m.title) LIKE LOWER(?) OR LOWER(m.title_original) LIKE LOWER(?)
            ;
            """;
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(
                    sql0, (resultSet, i) -> new ResultResponse(
                        resultSet.getString("full_name")), q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(
                    sql1, (resultSet, i) -> new ResultResponse(
                        resultSet.getString("full_name")), q);
            }
            default -> {
                return jdbcTemplate.query(
                    sql2, (resultSet, i) -> new ResultResponse(
                        resultSet.getString("full_name")), q, q);
            }
        }
    }
}
