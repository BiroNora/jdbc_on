package com.norab.security;

public enum Permissions {
    SHOW_WRITE("show:write"),
    SHOW_READ("show:read"),
    OPINION_WRITE("opinion:write"), //write, edit, delete
    OPINION_DELETE("opinion:delete"),
    STAFF_ACCESS("staff:access"); //only staff crud

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
