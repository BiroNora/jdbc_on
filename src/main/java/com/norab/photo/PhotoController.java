package com.norab.photo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/photos")
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public List<Photo> listPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("{id}")
    public Photo getPhotoUrl(@PathVariable("id") Long photoId) {
        return photoService.getPhoto(photoId);
    }

    @PostMapping
    public PhotoId addPhoto(@RequestBody Photo photo) {
        return new PhotoId(photoService.insertPhoto(photo));
    }

    @DeleteMapping("{id}")
    public void deletePhoto(@PathVariable("id") Long photoId) {
        photoService.deletePhoto(photoId);
    }

    @PutMapping("{id}")
    public void updatePhoto(
        @PathVariable("id") Long photoId, @RequestBody Photo photo) {
        photoService.updatePhoto(photoId, photo);
    }
}
