package com.norab.photo;

import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoDao photoDao;

    public PhotoService(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public List<Photo> getPhotos() {
        return photoDao.selectPhotos();
    }

    public int addNewPhoto(Photo photo) {
        String roleUrl = photo.photoUrl();
        List<Photo> photo1 = photoDao.selectPhotos();
        List<Photo> collect = photo1.stream()
            .filter(x -> x.photoUrl().equals(roleUrl)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this photo already exists");
        }
        int result = photoDao.insertPhoto(photo);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
        return result;
    }

    public void deletePhoto(Long id) {
        Optional<Photo> photo1 = photoDao.selectPhotoById(id);
        photo1.ifPresentOrElse(photo -> {
            int result = photoDao.deletePhoto(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Photo with id %s not found", id));
        });
    }

    public Photo getPhoto(Long id) {
        try {
            return (Photo) photoDao.selectPhotoById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Photo with id %s not found", id)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePhoto(Long id, Photo photo) {
        if (photoDao.selectPhotoById(id).isPresent()) {
            Photo photo1 = new Photo(id, photo.photoUrl(), photo.movieId(), photo.actorId(), photo.roleId());
            photoDao.updatePhoto(id, photo1);
        } else {
            throw new NotFoundException(String.format("Photo with id %s not found", id));
        }
    }
}
