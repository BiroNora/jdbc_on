package com.norab.role_photo;

import com.norab.actor.ActorDataAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolePhotoDataAccessService implements RolePhotoDao<RolePhoto> {
    private static final Logger log = LoggerFactory.getLogger(RolePhotoDataAccessService.class);
    private final JdbcTemplate jdbcTemplate;

    public RolePhotoDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RolePhoto> selectRolePhotos() {
        var sql = """
            SELECT photo_id, url, role_id
            FROM role_photos
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new RolePhotoRowMapper());
    }

    @Override
    public int insertRolePhoto(RolePhoto rolePhoto) {
        var sql = """
            INSERT into role_photos(url, role_id) VALUES (?, ?);
            """;
        int insert = jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId());
        if (insert == 1) {
            log.info("New photo inserted: " + rolePhoto);
        }
        return insert;
    }

    @Override
    public int deleteRolePhoto(Integer id) {
        var sql = """
            DELETE FROM role_photos
            WHERE photo_id = ?;
            """;
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            log.info(String.format("Photo with id: %d is deleted.", id));
        }
        return delete;
    }

    @Override
    public Optional<RolePhoto> selectRolePhotoById(Integer id) {
        var sql = """
            SELECT photo_id, url, role_id
            FROM role_photos
            WHERE photo_id = ?;
            """;
        Optional<RolePhoto> selected = jdbcTemplate.query(sql, new RolePhotoRowMapper(), id)
            .stream()
            .findFirst();
        if (selected.isPresent()) {
            log.info(String.format("Photo with id: %d is selected.", id));
        }
        return selected;
    }

    @Override
    public int updateRolePhoto(Integer id, RolePhoto rolePhoto) {
        var sql = """
            UPDATE role_photos
            SET url = ?, role_id = ?
            WHERE photo_id = ?;
            """;
        int update = jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId(), id);
        if (update == 1) {
            log.info(String.format("Photo with id: %d is updated.", id));
        }
        return update;
    }
}
