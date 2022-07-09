package com.norab.role;

import java.util.List;
import java.util.Optional;

public interface RoleDao<Plays> {
    List<Plays> selectRoles();

    long insertRole(Plays plays);

    int deleteRole(Long photoId);

    Optional<Plays> selectRoleById(Long photoId);

    int updateRole(Long photoId, Plays plays);
}
