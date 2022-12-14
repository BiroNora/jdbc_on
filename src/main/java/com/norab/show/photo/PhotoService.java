package com.norab.show.photo;

import com.norab.exception.InvalidInputException;
import com.norab.exception.NotFoundException;
import com.norab.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoDao photoDao;

    public PhotoService(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public List<Photo> listPhotos(Page page) {
        return photoDao.listPhotos(page);
    }

    public int insertPhoto(Photo photo) throws InvalidInputException {
        if (photo == null || !photo.isValid()) {
            throw new InvalidInputException("Invalid data");
        }
        return photoDao.insertPhoto(photo);
    }

    public void deletePhoto(Integer photoId) {
        Optional<Photo> photo1 = photoDao.selectPhotoById(photoId);
        photo1.ifPresentOrElse(photo -> {
            boolean result = photoDao.deletePhoto(photoId);
            if (!result) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Photo with id %s not found", photoId));
        });
    }

    public Photo getPhoto(Integer photoId) {
        Optional<Photo> photo = photoDao.selectPhotoById(photoId);
        return photo.orElseThrow(() -> new NotFoundException(String.format("Photo with id %s not found", photoId)));
    }

    public void updatePhoto(Integer photoId, Photo photo) {
        if (photoDao.selectPhotoById(photoId).isPresent()) {
            Photo photo1 = new Photo(photoId, photo.getPhotoUrl(), photo.getMovieId(), photo.getActorId(), photo.getRoleId());
            photoDao.updatePhoto(photoId, photo1);
        } else {
            throw new NotFoundException(String.format("Photo with id %s not found", photoId));
        }
    }
}
