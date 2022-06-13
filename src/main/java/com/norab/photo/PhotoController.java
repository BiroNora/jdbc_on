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
    public Photo getPhotoUrl(@PathVariable("id") Long id) {
        return photoService.getPhoto(id);
    }

    @PostMapping
    public void addPhoto(@RequestBody Photo photo) {

        photoService.addNewPhoto(photo);
    }

    @DeleteMapping("{id}")
    public void deletePhoto(@PathVariable("id") Long id) {
        photoService.deletePhoto(id);
    }

    @PutMapping("{id}")
    public void updatePhoto(
        @PathVariable("id") Long id, @RequestBody Photo photo) {
        photoService.updatePhoto(id, photo);
    }
}
