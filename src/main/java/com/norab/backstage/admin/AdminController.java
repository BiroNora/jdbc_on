package com.norab.backstage.admin;

import com.norab.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "management/api/v1/admins")
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

    @GetMapping("/find")
    public List<Admin> selectAdminByName(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "match", required = false, defaultValue = "false") String match) {
        boolean m = match != null && match.equalsIgnoreCase("true");
        return repository.selectAdminByName(name, m);
    }

    @PutMapping("{id}")
    public void updateAdmin(@PathVariable("id") UUID adminId, @RequestBody(required = false) Admin admin) {
        repository.updateAdmin(adminId, admin);
    }

    @PostMapping
    public String insertAdmin(@RequestBody Admin admin) {

        return repository.insertAdmin(admin);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(
        @PathVariable("id") UUID adminId) {
        repository.deleteAdmin(adminId);
    }
}
