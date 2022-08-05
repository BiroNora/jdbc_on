package com.norab.show.movie;

import com.norab.exception.AlreadyExistsException;
import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import com.norab.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository implements MovieDao<Movie> {
    private static final Logger log = LoggerFactory.getLogger(MovieRepository.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> listMovies(Page page) {
        var sql = "SELECT " +
            "movie_id, " +
            "title, " +
            "title_original, " +
            "release_date, " +
            "end_date, " +
            "m_type, " +
            "is_adult " +
            "FROM movies " +
            "ORDER BY title asc " +
            "LIMIT '" + page.getLimit() + "' " +
            "OFFSET '" + page.getOffset() + "'";
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    public boolean isMovieExists(Movie movie) {
        String title = Utils.addPercent(movie.getTitle());
        String origTitle = Utils.addPercent(movie.getTitleOriginal());
        var sql0 = """
            SELECT * FROM movies WHERE title LIKE ? OR title_original LIKE ?;
            """;
        List<Movie> movies = jdbcTemplate.query(sql0, new MovieRowMapper(), title, origTitle);
        return movies.stream()
            .filter(x -> x.getReleaseDate().equals(movie.getReleaseDate())).toList().size() > 0;
    }

    @Override
    public int insertMovie(Movie movie) {
        if (isMovieExists(movie)) {
            throw new AlreadyExistsException("This movie already exists");
        }
        var sql = """
            INSERT INTO movies(
            title,
            title_original,
            release_date,
            end_date,
            m_type,
            is_adult
            ) VALUES (?, ?, ?, ?, ?, ?)
            ;
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"movie_id"});
            ps.setString(1, movie.getTitle());
            if (movie.getTitleOriginal() != null) {
                ps.setString(2, movie.getTitleOriginal());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            ps.setShort(3, movie.getReleaseDate());
            if (movie.getEndDate() != null) {
                ps.setShort(4, movie.getEndDate());
            } else {
                ps.setNull(4, Types.SMALLINT);
            }
            ps.setString(5, movie.getmType());
            ps.setBoolean(6, movie.isAdult());
            return ps;
        }, keyHolder);

        if (result != 1) {
            throw new IllegalStateException("Failed to add movie");
        }
        log.info("New movie inserted: " + movie);

        return keyHolder.getKeyAs(Integer.class);
    }

    @Override
    public DeleteResult deleteMovie(Integer movieId, boolean force) {
        var sql = """
            DELETE FROM movies
            WHERE movie_id = ?;
            """;
        String checkUsage = """
            SELECT sum(c) AS numOfRefs FROM
              (SELECT count(*) AS c FROM directors
              WHERE movie_id = ?
              UNION
              SELECT count(*) FROM plays
              WHERE movie_id = ?
              UNION
              SELECT count(*) FROM genre
              WHERE movie_id = ?) AS d
            ;
            """;
        try {
            if (!force) {
                List<Boolean> result = jdbcTemplate.query(
                    checkUsage,
                    (resultSet, i) -> resultSet.getInt("numOfRefs") > 0,
                    movieId, movieId, movieId);
                if (result.contains(true)) {
                    return DeleteResult.HAS_REFERENCES;
                }
            }
            int delete = jdbcTemplate.update(sql, movieId);
            if (delete == 0) {
                return DeleteResult.INVALID_ID;
            }
        } catch (DataAccessException e) {
            log.info(String.format("Movie with id: %d is failed to delete: %s", movieId, e.getMessage()));
            return DeleteResult.JDBC_ERROR;
        }
        log.info(String.format("Movie with id: %d is deleted.", movieId));
        return DeleteResult.SUCCESS;
    }

    @Override
    public Optional<Movie> selectMovieById(Integer movieId) {
        var sql = """
            SELECT * FROM movies
            WHERE movie_id = ?;
            """;
        Optional<Movie> selected = jdbcTemplate.query(sql, new MovieRowMapper(), movieId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Movie with id: %d is selected.", movieId));
        }
        return selected;
    }

    @Override
    public int updateMovie(Integer movieId, Movie movie) {
        var sql = """
            UPDATE movies
            SET
            title = ?,
            title_original = ?,
            release_date = ?,
            end_date = ?,
            m_type = ?,
            is_adult = ?
            WHERE movie_id = ?;
            """;
        int update = jdbcTemplate.update(
            sql,
            movie.getTitle(),
            movie.getTitleOriginal(),
            movie.getReleaseDate(),
            movie.getEndDate(),
            movie.getmType(),
            movie.isAdult(),
            movie.getMovieId()
        );
        if (update == 1) {
            log.info(String.format("Movie with id: %d is updated.", movieId));
        }
        return update;
    }

}
