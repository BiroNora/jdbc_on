package com.norab.role;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Plays> listRoles() {
        return roleService.getRoles();
    }

    @GetMapping("{id}")
    public Plays getRoleId(@PathVariable("id") Long id) {
        return roleService.getRole(id);
    }

    @PostMapping
    public void addRole(@RequestBody Plays plays) {
        roleService.addNewRole(plays);
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }

    @PutMapping("{id}")
    public void updateRole(@PathVariable("id") Long id, @RequestBody Plays plays) {
        roleService.updateRole(id, plays);
    }
}
