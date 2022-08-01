package com.norab.role;

import com.norab.utils.Page;

import java.util.List;
import java.util.Optional;

public interface RoleDao<Plays> {
    List<Plays> listRoles(Page page);

    boolean selectRoleByRole(Plays plays);

    int insertRole(Plays plays);

    int deleteRole(Integer photoId);

    Optional<Plays> selectRoleById(Integer photoId);

    int updateRole(Integer photoId, Plays plays);
}
