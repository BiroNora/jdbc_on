package com.norab.role;

import java.util.List;
import java.util.Optional;

public class RoleDataAccessService implements RoleDao{
    @Override
    public List<Role> selectRole() {
        return null;
    }

    @Override
    public int insertRole(Role role) {
        return 0;
    }

    @Override
    public int deleteRole(int id) {
        return 0;
    }

    @Override
    public Optional<Role> selectRoleById(int id) {
        return Optional.empty();
    }

    @Override
    public int updateRole(int id, Role role) {
        return 0;
    }
}
