package com.norab.photo;

import java.util.List;
import java.util.Optional;

public interface PhotoDao<Photo> {
    List<Photo> selectPhotos();

    long insertPhoto(Photo photo);

    boolean deletePhoto(Long photoId);

    Optional<Photo> selectPhotoById(Long photoId);

    boolean updatePhoto(Long photoId, Photo photo);
}
