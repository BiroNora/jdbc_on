package com.norab.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<Photo> selectPhotos() {
        var sql = """
            SELECT photo_id, url, movie_id, actor_id, role_id
            FROM photos
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new PhotoRowMapper());
    }

    @Override
    public int insertPhoto(Photo photo) {
        var sql = """
            INSERT into photos(url, movie_id, actor_id, role_id) VALUES (?, ?, ?, ?);
            """;
        int insert = jdbcTemplate.update(
            sql,
            photo.photoUrl(),
            photo.movieId(),
            photo.actorId(),
            photo.roleId());
        if (insert == 1) {
            log.info("New photo inserted: " + photo);
        }
        return insert;
    }

    @Override
    public int deletePhoto(Long id) {
        var sql = """
            DELETE FROM photos
            WHERE photo_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info(String.format("Photo with id: %d is deleted.", id));
        }
        return delete;
    }

    @Override
    public Optional<Photo> selectPhotoById(Long id) {
        var sql = """
            SELECT photo_id, url, movie_id, actor_id, role_id
            FROM photos
            WHERE photo_id = ?;
            """;
        Optional<Photo> selected = jdbcTemplate.query(sql, new PhotoRowMapper(), id)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Photo with id: %d is selected.", id));
        }
        return selected;
    }

    @Override
    public int updatePhoto(Long id, Photo photo) {
        var sql = """
            UPDATE photos
            SET url = ?, movie_id = ?, actor_id = ?, role_id = ?
            WHERE photo_id = ?;
            """;
        int update = jdbcTemplate.update(
            sql,
            photo.photoUrl(),
            photo.movieId(),
            photo.actorId(),
            photo.roleId(),
            id);
        if (update == 1) {
            log.info(String.format("Photo with id: %d is updated.", id));
        }
        return update;
    }
}
