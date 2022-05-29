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
            SELECT role_full_name, url, role_id
            FROM role_photos
            LIMIT 10;
            """;
        return jdbcTemplate.query(sql, new RolePhotoRowMapper());
    }

    @Override
    public int insertRolePhoto(RolePhoto rolePhoto) {
        var sql = """
            INSERT into role_photos(url, role_id) VALUES (?, ?, ?);
            """;
        return jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId());
    }

    @Override
    public int deleteRolePhoto(String photoUrl) {
        var sql = """
            DELETE FROM role_photos
            WHERE url = ?;
            """;
        return jdbcTemplate.update(sql, photoUrl);
    }

    @Override
    public Optional<RolePhoto> selectRolePhotoByUrl(String photoUrl) {
        var sql = """
            SELECT url, role_id
            FROM role_photos
            WHERE url = ?;
            """;
        return jdbcTemplate.query(sql, new RolePhotoRowMapper(), photoUrl)
            .stream()
            .findFirst();
    }

    @Override
    public int updateRolePhoto(String photoUrl, RolePhoto rolePhoto) {
        var sql = """
            UPDATE role_photos
            SET url = ?
            WHERE url = ?;
            """;
        return jdbcTemplate.update(sql, rolePhoto.photoUrl(), rolePhoto.roleId());
    }
}
