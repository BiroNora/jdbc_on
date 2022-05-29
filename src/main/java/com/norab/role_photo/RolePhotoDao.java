package com.norab.role_photo;

import java.util.List;
import java.util.Optional;

public interface RolePhotoDao {
    List<RolePhoto> selectRolePhotos();
    int insertRolePhoto(RolePhoto rolePhoto);
    int deleteRolePhoto(String photoUrl);
    Optional<RolePhoto> selectRolePhotoByUrl(String photoUrl);
    int updateRolePhoto(String photoUrl, RolePhoto rolePhoto);
}
