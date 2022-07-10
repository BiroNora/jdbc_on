package com.norab.role;

import java.util.List;
import java.util.Optional;

public interface RoleDao<Plays> {
    List<Plays> selectRoles();

    int insertRole(Plays plays);

    int deleteRole(Integer photoId);

    Optional<Plays> selectRoleById(Integer photoId);

    int updateRole(Integer photoId, Plays plays);
}
