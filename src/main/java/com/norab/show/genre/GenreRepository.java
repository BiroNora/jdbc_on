package com.norab.show.genre;

import com.norab.exception.InvalidInputException;
import com.norab.show.crossed.SearchLocation;
import com.norab.show.director.DirectorRepository;
import com.norab.utils.ResultResponse;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository implements GenreDao<Genre> {
    private static final Logger log = LoggerFactory.getLogger(DirectorRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public GenreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<GenresByMovieId> selectGenres() {
        var sql = """
            SELECT movie_id, STRING_AGG(genre, '|') as genres
            FROM genre GROUP BY movie_id;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            String[] genres = resultSet.getString("genres").split("\\|");
            return new GenresByMovieId(
                resultSet.getInt("movie_id"),
                List.of(genres));
        });
    }

    @Override
    public List<ResultResponse> selectAllGenre() {
        var sql = """
            SELECT DISTINCT genre
            FROM genre
            ORDER BY genre;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("genre")));
    }

    @Override
    public boolean insertGenre(Genre genre) {
        if (!genre.isValid()) {
            throw new InvalidInputException("Invalid genre");
        }
        var sql = """
            INSERT into genre(movie_id, genre) VALUES (?, ?);
            """;
        try {
            int update = jdbcTemplate.update(
                sql,
                genre.getMovieId(),
                genre.getGenre().toLowerCase());
            log.info("New genre inserted: " + genre);
            return update == 1;
        } catch (DataAccessException e) {
            throw new InvalidInputException("Illegal id");
        }
    }

    @Override
    public boolean deleteGenre(Integer movieId, String genre) {
        var sql = """
            DELETE FROM genre
            WHERE movie_id = ? AND genre = ?;
            """;
        int delete = jdbcTemplate.update(sql, movieId, genre);
        if (delete == 1) {
            log.info(String.format("Genre %d with movie %s is deleted.", movieId, genre));
        }
        return delete == 1;
    }

    @Override
    public boolean selectGenreById(Integer movieId, String genre) {
        var sql = """
            SELECT movie_id, genre
            FROM genre
            WHERE movie_id = ? AND genre = ?;
            """;
        Optional<Genre> selected = jdbcTemplate.query(sql, new GenreRowMapper(), movieId, genre)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Genre %d with movie %s is selected.", movieId, genre));
        }
        return selected.isPresent();
    }

    @Override
    public List<ResultResponse> selectGenresByMovieId(Integer movieId) {
        var sql = """
            SELECT genre FROM genre
            WHERE movie_id = ?
            ORDER BY genre ASC;
            """;
        return jdbcTemplate.query(sql, (resultSet, i) -> new ResultResponse(
            resultSet.getString("genre")), movieId);
    }

    @Override
    public List<MoviesByGenre> selectMoviesByGenre(String genre) {
        String q = Utils.addPercent(genre);
        var sql = """
             SELECT title, title_original, release_date, genre
             FROM movies
             JOIN
             (SELECT movie_id, genre
                 FROM genre
                 WHERE LOWER(genre) LIKE LOWER(?)) AS g
             USING(movie_id)
             ORDER BY title ASC
            ;
             """;
        return jdbcTemplate.query(
            sql, (resultSet, i) -> new MoviesByGenre(
                resultSet.getString("title"),
                resultSet.getString("title_original"),
                resultSet.getShort("release_date"),
                resultSet.getString("genre")
            ), q);
    }

    @Override
    public List<GenresByMovie> selectGenresByMovieTitle(String title, SearchLocation location) {
        String q = Utils.addPercent(title);
        var sql0 = """
            SELECT title, title_original, release_date,
                   STRING_AGG(genre, '|') as genre
            FROM movies as movies
            JOIN
            (SELECT movie_id, genre
                FROM genre) as g
            USING(movie_id)
            WHERE LOWER(movies.title) LIKE LOWER(?)
            GROUP BY movies.movie_id
            ORDER BY movies.title
            ;
            """;
        var sql1 = """
            SELECT title, title_original, release_date,
                   STRING_AGG(genre, '|') as genre
            FROM movies as movies
            JOIN
            (SELECT movie_id, genre
                FROM genre) as g
            USING(movie_id)
            WHERE LOWER(movies.title_original) LIKE LOWER(?)
            GROUP BY movies.movie_id
            ORDER BY movies.title
            ;
            """;
        var sql2 = """
            SELECT title, title_original, release_date,
                   STRING_AGG(genre, '|') as genre
            FROM movies as movies
            JOIN
            (SELECT movie_id, genre
                FROM genre) as g
            USING(movie_id)
            WHERE LOWER(movies.title) LIKE LOWER(?) OR LOWER(movies.title_original) like LOWER(?)
            GROUP BY movies.movie_id
            ORDER BY movies.title
            ;
            """;
        switch (location) {
            case TITLE -> {
                return jdbcTemplate.query(sql0, (resultSet, i) -> {
                    String[] genre = resultSet.getString("genre").split("\\|");
                    return new GenresByMovie(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getShort("release_date"),
                        List.of(genre));
                }, q);
            }
            case ORIGTITLE -> {
                return jdbcTemplate.query(sql1, (resultSet, i) -> {
                    String[] genre = resultSet.getString("genre").split("\\|");
                    return new GenresByMovie(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getShort("release_date"),
                        List.of(genre));
                }, q);
            }
            default -> {
                return jdbcTemplate.query(sql2, (resultSet, i) -> {
                    String[] genre = resultSet.getString("genre").split("\\|");
                    return new GenresByMovie(
                        resultSet.getString("title"),
                        resultSet.getString("title_original"),
                        resultSet.getShort("release_date"),
                        List.of(genre));
                }, q, q);
            }
        }
    }
}
