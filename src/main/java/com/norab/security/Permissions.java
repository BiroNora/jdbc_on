package com.norab.security;

public enum Permissions {
    SHOW_WRITE("show:write"),
    OPINION_WRITE("opinion:write"),
    OPINION_DELETE("opinion:delete"),
    CRUD_ACCESS("crud:access");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
