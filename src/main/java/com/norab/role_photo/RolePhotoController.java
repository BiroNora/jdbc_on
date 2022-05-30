package com.norab.role_photo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/photos")
public class RolePhotoController {
    private final RolePhotoService rolePhotoService;

    public RolePhotoController(RolePhotoService rolePhotoService) {
        this.rolePhotoService = rolePhotoService;
    }

    @GetMapping
    public List<RolePhoto> listRolePhotos() {
        return rolePhotoService.getRolePhotos();
    }

    @GetMapping("{photoUrl}")
    public RolePhoto getRolePhotoUrl(@PathVariable("photoUrl") String photoUrl) {
        return rolePhotoService.getPhoto(photoUrl);
    }

    @PostMapping
    public void addRolePhoto(@RequestBody RolePhoto rolePhoto) {
        rolePhotoService.addNewRolePhoto(rolePhoto);
    }

    @DeleteMapping("{photoUrl}")
    public void deleteRolePhoto(@PathVariable("photoUrl") String photoUrl) {
        rolePhotoService.deleteRolePhoto(photoUrl);
    }

    @PutMapping("{photoUrl}")
    public void updateRolePhoto(@PathVariable("photoUrl") String photoUrl, @RequestBody RolePhoto rolePhoto) {
        rolePhotoService.updateRolePhoto(photoUrl, rolePhoto);
    }
}
