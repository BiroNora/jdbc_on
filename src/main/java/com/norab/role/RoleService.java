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
            Plays plays1 = new Plays(id, plays.getRoleName(), plays.getMovieId(), plays.getActorId());
            roleDao.updateRole(id, plays1);
        } else {
            throw new NotFoundException(String.format("Role with id %s not found", id));
        }
    }
}
