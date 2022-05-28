package com.norab.role;

import com.norab.actor.Actor;
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
    public List<Role> listRoles() { return roleService.getRoles(); }

    @GetMapping("{id}")
    public Role getRoleId(@PathVariable("id") Integer id) {
        return roleService.getRole(id);
    }

    @PostMapping
    public void addRole(@RequestBody Role role) {
        roleService.addNewRole(role);
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
    }

    @PutMapping("{id}")
    public void updateRole(@PathVariable("id") Integer id, @RequestBody Role role) {
        roleService.updateRole(id, role);
    }
}
