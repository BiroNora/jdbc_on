package com.norab.show.role;

import com.norab.utils.Page;
import com.norab.utils.ResultResponse;

import java.util.List;
import java.util.Optional;

public interface RoleDao<Plays> {
    List<Plays> listRoles(Page page);

    //Inside helper method
    boolean selectRoleByRole(Plays plays);

    int insertRole(Plays plays);

    int deleteRole(Integer photoId);

    Optional<Plays> selectRoleById(Integer photoId);

    List<ResultResponse> selectRolesByName(String rolename);

    int updateRole(Integer photoId, Plays plays);
}
