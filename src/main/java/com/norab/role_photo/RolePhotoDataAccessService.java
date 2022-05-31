package com.norab.role_photo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolePhotoDataAccessService implements RolePhotoDao {
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
        return jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId());
    }

    @Override
    public int deleteRolePhoto(Integer id) {
        var sql = """
            DELETE FROM role_photos
            WHERE photo_id = ?;
            """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<RolePhoto> selectRolePhotoById(Integer id) {
        var sql = """
            SELECT photo_id, url, role_id
            FROM role_photos
            WHERE photo_id = ?;
            """;
        return jdbcTemplate.query(sql, new RolePhotoRowMapper(), id)
            .stream()
            .findFirst();
    }

    @Override
    public int updateRolePhoto(Integer id, RolePhoto rolePhoto) {
        var sql = """
            UPDATE role_photos
            SET url = ?, role_id = ?
            WHERE photo_id = ?;
            """;
        return jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId(), id);
    }
}
