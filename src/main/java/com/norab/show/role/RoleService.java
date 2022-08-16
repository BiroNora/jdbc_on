package com.norab.show.role;

import com.norab.exception.NotFoundException;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Plays> getRoles(Page page) {
        return roleDao.listRoles(page);
    }

    public int insertRole(Plays plays) {
        return roleDao.insertRole(plays);
    }

    public void deleteRole(Integer roleId) {
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

    public Plays getRole(Integer roleId) {
        Optional<Plays> play = roleDao.selectRoleById(roleId);
        return play.orElseThrow(() -> new NotFoundException(String.format("Role with id %s not found", roleId)));
    }

    public List<ResultResponse> selectRoleByName(String rolename) {
        return roleDao.selectRolesByName(rolename);
    }

    public void updateRole(Integer roleId, Plays plays) {
        if (roleDao.selectRoleById(roleId).isPresent()) {
            Plays plays1 = new Plays(roleId, plays.getRoleName(), plays.getMovieId(), plays.getActorId());
            roleDao.updateRole(roleId, plays1);
        } else {
            throw new NotFoundException(String.format("Role with id %s not found", roleId));
        }
    }
}
