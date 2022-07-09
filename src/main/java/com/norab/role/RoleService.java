package com.norab.role;

import com.norab.exception.AlreadyExistsException;
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

    public long insertRole(Plays plays) {
        String role = plays.getRoleName();
        List<Plays> playsList = roleDao.selectRoles();
        List<Plays> collect = playsList.stream()
            .filter(x -> x.getRoleName().equals(role)).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This role already exists");
        }
        return roleDao.insertRole(plays);
    }

    public void deleteRole(Long roleId) {
        Optional<Plays> role1 = roleDao.selectRoleById(roleId);
        role1.ifPresentOrElse(role -> {
            int result = roleDao.deleteRole(roleId);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete role");
            }
        }, () -> {
            throw new NotFoundException(String.format("Role with id %s not found", roleId));
        });
    }

    public Plays getRole(Long roleId) {
        try {
            return (Plays) roleDao.selectRoleById(roleId)
                .orElseThrow(() -> new NotFoundException(String.format("Role with id %s not found", roleId)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRole(Long roleId, Plays plays) {
        if (roleDao.selectRoleById(roleId).isPresent()) {
            Plays plays1 = new Plays(roleId, plays.getRoleName(), plays.getMovieId(), plays.getActorId());
            roleDao.updateRole(roleId, plays1);
        } else {
            throw new NotFoundException(String.format("Role with id %s not found", roleId));
        }
    }
}
