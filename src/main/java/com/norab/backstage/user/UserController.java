package com.norab.backstage.user;

import com.norab.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "management/api/v1/users")
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping
    public List<User> listUsers(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return repository.listUsers(Page.of(page, size));
    }

    @GetMapping("{id}")
    public Optional<User> selectUserById(@PathVariable("id") UUID userId) {
        return repository.selectUserById(userId);
    }

    @GetMapping("/find")
    public List<User> selectUserByName(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "match", required = false, defaultValue = "false") String match) {
        boolean m = match != null && match.equalsIgnoreCase("true");
        return repository.selectUserByName(name, m);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable("id") UUID userId, @RequestBody(required = false) User user) {
        repository.updateUser(userId, user);
    }

    @PostMapping
    public String insertUser(@RequestBody User user) {
        return repository.insertUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(
        @PathVariable("id") UUID userId) {
        repository.deleteUser(userId);
    }
}
