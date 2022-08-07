package com.norab.backstage.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admins")
public class Controller {
    @Autowired
    AdminRepository repository;

    @GetMapping
    public List<String> listActors() {
        return repository.listAdmins();
    }
}
