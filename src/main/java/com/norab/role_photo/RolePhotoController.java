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

    @GetMapping("{id}")
    public RolePhoto getRolePhotoUrl(@PathVariable("id") Long id) {
        return rolePhotoService.getPhoto(id);
    }

    @PostMapping
    public void addRolePhoto(@RequestBody RolePhoto rolePhoto) {

        rolePhotoService.addNewRolePhoto(rolePhoto);
    }

    @DeleteMapping("{id}")
    public void deleteRolePhoto(@PathVariable("id") Long id) {
        rolePhotoService.deleteRolePhoto(id);
    }

    @PutMapping("{id}")
    public void updateRolePhoto(
        @PathVariable("id") Long id, @RequestBody RolePhoto rolePhoto) {
        rolePhotoService.updateRolePhoto(id, rolePhoto);
    }
}
