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
    public Plays getRoleId(@PathVariable("id") Integer roleId) {
        return roleService.getRole(roleId);
    }

    @PostMapping
    public PlaysId addRole(@RequestBody Plays plays) {
        return new PlaysId(roleService.insertRole(plays));
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable("id") Integer roleId) {
        roleService.deleteRole(roleId);
    }

    @PutMapping("{id}")
    public void updateRole(@PathVariable("id") Integer roleId, @RequestBody Plays plays) {
        roleService.updateRole(roleId, plays);
    }
}
