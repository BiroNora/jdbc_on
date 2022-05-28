package com.norab.role;

import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> getRoles() {
        return roleDao.selectRoles();
    }

    public void addNewRole(Role role) {
        String roleName = role.roleName();
        List<Role> roles = roleDao.selectRoles();
        List<Role> collect = roles.stream()
            .filter(x -> x.roleName().equals(roleName)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this role already exists");
        }
        int result = roleDao.insertRole(role);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteRole(Integer id) {
        Optional<Role> role1 = roleDao.selectRoleById(id);
        role1.ifPresentOrElse(role -> {
            int result = roleDao.deleteRole(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Role with id %s not found", id));
        });
    }

    public Role getRole(int id) {
        return roleDao.selectRoleById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Role with id %s not found", id)));
    }

    public void updateRole(int id, Role role) {
        if (roleDao.selectRoleById(id).isPresent()) {
            Role role1 = new Role(id, role.movieId(), role.actorId(), role.roleName());
            roleDao.updateRole(id, role1);
        } else {
            throw new NotFoundException(String.format("Role with id %s not found", id));
        }
    }
}
