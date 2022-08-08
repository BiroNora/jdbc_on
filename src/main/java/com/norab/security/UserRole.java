package com.norab.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.norab.security.UserPermission.*;

public enum UserRole {
    USER(Sets.newHashSet(OPINION_WRITE)),
    STUFF(Sets.newHashSet(SHOW_WRITE, OPINION_DELETE)),
    ADMINISTRATOR(Sets.newHashSet(CRUD_ACCESS));

    private final Set<UserPermission> permission;

    UserRole(Set<UserPermission> permission) {
        this.permission = permission;
    }

    public Set<UserPermission> getPermission() {
        return permission;
    }
}
