package com.norab.role;

import java.util.List;
import java.util.Optional;

public interface RoleDao<Plays> {
    List<Plays> selectRoles();
    int insertRole(Plays plays);
    int deleteRole(Long id);
    Optional<Plays> selectRoleById(Long id);
    int updateRole(Long id, Plays plays);
}
