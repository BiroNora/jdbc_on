package com.norab.security;

public enum UserPermission {
    SHOW_WRITE("show:write"),
    OPINION_WRITE("opinion:write"),
    OPINION_DELETE("opinion:delete"),
    CRUD_ACCESS("crud:access");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
