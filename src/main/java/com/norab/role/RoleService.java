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

    public List<Plays> getRoles() {
        return roleDao.selectRoles();
    }

    public void addNewRole(Plays plays) {
        String roleName1 = plays.roleName();
        List<Plays> plays1 = roleDao.selectRoles();
        List<Plays> collect = plays1.stream()
            .filter(x -> x.roleName().equals(roleName1)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this role already exists");
        }
        int result = roleDao.insertRole(plays);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteRole(Long id) {
        Optional<Plays> role1 = roleDao.selectRoleById(id);
        role1.ifPresentOrElse(role -> {
            int result = roleDao.deleteRole(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Role with id %s not found", id));
        });
    }

    public Plays getRole(Long id) {
        try {
            return (Plays) roleDao.selectRoleById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role with id %s not found", id)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRole(Long id, Plays plays) {
        if (roleDao.selectRoleById(id).isPresent()) {
            Plays plays1 = new Plays(id, plays.roleName(), plays.movieId(), plays.actorId());
            roleDao.updateRole(id, plays1);
        } else {
            throw new NotFoundException(String.format("Role with id %s not found", id));
        }
    }
}
