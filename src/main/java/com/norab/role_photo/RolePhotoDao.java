package com.norab.role_photo;

import java.util.List;
import java.util.Optional;

public interface RolePhotoDao {
    List<RolePhoto> selectRolePhotos();
    int insertRolePhoto(RolePhoto rolePhoto);
    int deleteRolePhoto(Integer id);
    Optional<RolePhoto> selectRolePhotoById(Integer id);
    int updateRolePhoto(Integer id, RolePhoto rolePhoto);
}
