package com.norab.photo;

import com.norab.exception.AlreadyExistsException;
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

    public long insertPhoto(Photo photo) {
        String roleUrl = photo.getPhotoUrl();
        List<Photo> photos = photoDao.selectPhotos();
        List<Photo> collect = photos.stream()
            .filter(x -> x.getPhotoUrl().equals(roleUrl)).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This photo already exists");
        }
        return photoDao.insertPhoto(photo);
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
            Photo photo1 = new Photo(id, photo.getPhotoUrl(), photo.getMovieId(), photo.getActorId(), photo.getRoleId());
            photoDao.updatePhoto(id, photo1);
        } else {
            throw new NotFoundException(String.format("Photo with id %s not found", id));
        }
    }
}
