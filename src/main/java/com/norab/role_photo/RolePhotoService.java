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

    public void deleteRolePhoto(String photoUrl) {
        Optional<RolePhoto> rolePhoto1 = rolePhotoDao.selectRolePhotoByUrl(photoUrl);
        rolePhoto1.ifPresentOrElse(photo -> {
            int result = rolePhotoDao.deleteRolePhoto(photoUrl);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Photo with url %s not found", photoUrl));
        });
    }

    public RolePhoto getPhoto(String photoUrl) {
        return rolePhotoDao.selectRolePhotoByUrl(photoUrl)
            .orElseThrow(() -> new NotFoundException(String.format("Photo with url %s not found", photoUrl)));
    }

    public void updateRolePhoto() {

    }
}
