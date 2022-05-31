package com.norab.role_photo;

import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolePhotoService {
    private final RolePhotoDao rolePhotoDao;

    public RolePhotoService(RolePhotoDao rolePhotoDao) {
        this.rolePhotoDao = rolePhotoDao;
    }

    public List<RolePhoto> getRolePhotos() {
        return rolePhotoDao.selectRolePhotos();
    }

    public void addNewRolePhoto(RolePhoto rolePhoto) {
        String roleUrl = rolePhoto.photoUrl();
        List<RolePhoto> rolePhoto1 = rolePhotoDao.selectRolePhotos();
        List<RolePhoto> collect = rolePhoto1.stream()
            .filter(x -> x.photoUrl().equals(roleUrl)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this photo already exists");
        }
        int result = rolePhotoDao.insertRolePhoto(rolePhoto);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteRolePhoto(Integer id) {
        Optional<RolePhoto> rolePhoto1 = rolePhotoDao.selectRolePhotoById(id);
        rolePhoto1.ifPresentOrElse(photo -> {
            int result = rolePhotoDao.deleteRolePhoto(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Photo with id %s not found", id));
        });
    }

    public RolePhoto getPhoto(Integer id) {
        try {
            return (RolePhoto) rolePhotoDao.selectRolePhotoById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Photo with id %s not found", id)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRolePhoto(Integer id, RolePhoto rolePhoto) {
        if (rolePhotoDao.selectRolePhotoById(id).isPresent()) {
            RolePhoto rolePhoto1 = new RolePhoto(id, rolePhoto.photoUrl(), rolePhoto.roleId());
            rolePhotoDao.updateRolePhoto(id, rolePhoto1);
        } else {
            throw new NotFoundException(String.format("Photo with id %s not found", id));
        }
    }
}
