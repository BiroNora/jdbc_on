package com.norab.role_photo;

import java.util.List;
import java.util.Optional;

public interface RolePhotoDao<RolePhoto> {
    List<RolePhoto> selectRolePhotos();

    int insertRolePhoto(RolePhoto rolePhoto);

    int deleteRolePhoto(Long id);

    Optional<RolePhoto> selectRolePhotoById(Long id);

    int updateRolePhoto(Long id, RolePhoto rolePhoto);
}
