package com.norab.role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    List<Plays> selectRoles();
    int insertRole(Plays plays);
    int deleteRole(int id);
    Optional<Plays> selectRoleById(int id);
    int updateRole(int id, Plays plays);
}
