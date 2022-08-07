package com.norab.security;

public enum UserPermission {
    OPINION_WRITE("op_write"),
    CRUD_ACCESS("crud_access");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
