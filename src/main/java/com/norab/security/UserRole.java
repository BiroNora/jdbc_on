package com.norab.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.norab.security.UserPermission.CRUD_ACCESS;
import static com.norab.security.UserPermission.OPINION_WRITE;

public enum UserRole {
    MODERATOR(Sets.newHashSet(OPINION_WRITE)),
    ADMINISTRATOR(Sets.newHashSet(CRUD_ACCESS));

    private final Set<UserPermission> permission;

    UserRole(Set<UserPermission> permission) {
        this.permission = permission;
    }

    public Set<UserPermission> getPermission() {
        return permission;
    }
}
