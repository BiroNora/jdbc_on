package com.norab.photo;

import java.util.List;
import java.util.Optional;

public interface PhotoDao<Photo> {
    List<Photo> selectPhotos();

    long insertPhoto(Photo photo);

    int deletePhoto(Long id);

    Optional<Photo> selectPhotoById(Long id);

    int updatePhoto(Long id, Photo photo);
}
