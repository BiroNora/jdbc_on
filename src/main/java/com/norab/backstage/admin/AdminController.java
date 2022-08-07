package com.norab.backstage.admin;

import com.norab.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/admins")
public class AdminController {
    @Autowired
    AdminRepository repository;

    @GetMapping
    public List<Admin> listAdmins(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return repository.listAdmins(Page.of(page, size));
    }

    @GetMapping("{id}")
    public Optional<Admin> selectAdminById(@PathVariable("id") UUID adminId) {
        return repository.selectAdminById(adminId);
    }
}
