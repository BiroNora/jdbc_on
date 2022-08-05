package com.norab.show.photo;

import java.util.List;
import java.util.Optional;

public interface PhotoDao<Photo> {
    List<Photo> listPhotos();

    int insertPhoto(Photo photo);

    boolean deletePhoto(Integer photoId);

    Optional<Photo> selectPhotoById(Integer photoId);

    boolean updatePhoto(Integer photoId, Photo photo);
}
