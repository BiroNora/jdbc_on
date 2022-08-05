package com.norab.show.photo;

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
        return photoService.listPhotos();
    }

    @GetMapping("{id}")
    public Photo getPhotoUrl(@PathVariable("id") Integer photoId) {
        return photoService.getPhoto(photoId);
    }

    @PostMapping
    public PhotoID addPhoto(@RequestBody Photo photo) {
        return new PhotoID(photoService.insertPhoto(photo));
    }

    @DeleteMapping("{id}")
    public void deletePhoto(@PathVariable("id") Integer photoId) {
        photoService.deletePhoto(photoId);
    }

    @PutMapping("{id}")
    public void updatePhoto(
        @PathVariable("id") Integer photoId, @RequestBody Photo photo) {
        photoService.updatePhoto(photoId, photo);
    }
}
