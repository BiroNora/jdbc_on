package com.norab.role;

import com.norab.actor.Actor;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    List<Role> selectRole();
    int insertRole(Role role);
    int deleteRole(int id);
    Optional<Role> selectRoleById(int id);
    int updateRole(int id, Role role);
}
