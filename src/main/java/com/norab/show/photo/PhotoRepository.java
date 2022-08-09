package com.norab.show.photo;

import com.norab.exception.InvalidInputException;
import com.norab.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PhotoRepository implements PhotoDao<Photo> {
    private static final Logger log = LoggerFactory.getLogger(PhotoRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public PhotoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Photo> listPhotos(Page page) {
        var sql = "SELECT photo_id, url, movie_id, actor_id, role_id " +
            "FROM photos ORDER BY movie_id LIMIT '" + page.getLimit() + "'" +
            "OFFSET '" + page.getOffset() + "'";
        return jdbcTemplate.query(sql, new PhotoRowMapper());
    }

    @Override
    public int insertPhoto(Photo photo) {
        if (photo.getPhotoUrl() == null ||
            photo.getPhotoUrl().strip().equals("")) {
            throw new InvalidInputException("Empty photoUrl");
        }
        if (photo.getMovieId() == null &&
            photo.getActorId() == null &&
            photo.getRoleId() == null) {
            throw new InvalidInputException("All IDs are null");
        }
        var sql = """
            INSERT into photos(url, movie_id, actor_id, role_id) VALUES (?, ?, ?, ?);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"photo_id"});
                ps.setString(1, photo.getPhotoUrl());
                if (photo.getMovieId() != null) {
                    ps.setInt(2, photo.getMovieId());
                } else {
                    ps.setNull(2, Types.BIGINT);
                }
                if (photo.getActorId() != null) {
                    ps.setInt(3, photo.getActorId());
                } else {
                    ps.setNull(3, Types.BIGINT);
                }
                if (photo.getRoleId() != null) {
                    ps.setInt(4, photo.getRoleId());
                } else {
                    ps.setNull(4, Types.BIGINT);
                }
                return ps;
            }, keyHolder);

            log.info("New photo inserted: " + photo);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Illegal id");
        }
        return keyHolder.getKeyAs(Integer.class);
    }


    @Override
    public boolean deletePhoto(Integer photoId) {
        var sql = """
            DELETE FROM photos
            WHERE photo_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, photoId);
        if (delete == 1) {
            log.info(String.format("Photo with id: %d is deleted.", photoId));
        }
        return delete == 1;
    }

    @Override
    public Optional<Photo> selectPhotoById(Integer photoId) {
        var sql = """
            SELECT photo_id, url, movie_id, actor_id, role_id
            FROM photos
            WHERE photo_id = ?;
            """;
        Optional<Photo> selected = jdbcTemplate.query(sql, new PhotoRowMapper(), photoId)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Photo with id: %d is selected.", photoId));
        }
        return selected;
    }

    @Override
    public boolean updatePhoto(Integer photoId, Photo photo) throws InvalidInputException {
        var sql = """
            UPDATE photos
            SET url = ?, movie_id = ?, actor_id = ?, role_id = ?
            WHERE photo_id = ?;
            """;
        try {
            int update = jdbcTemplate.update(
                sql,
                photo.getPhotoUrl(),
                photo.getMovieId(),
                photo.getActorId(),
                photo.getRoleId(),
                photoId);
            if (update == 1) {
                log.info(String.format("Photo with id: %d is updated.", photoId));
            }
            return update == 1;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new InvalidInputException("Invalid ID");
        }
    }
}
