package com.norab.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.norab.security.Permissions.*;

public enum Roles {
    USER(Sets.newHashSet(OPINION_WRITE)),
    STUFF(Sets.newHashSet(SHOW_WRITE, OPINION_DELETE)),
    ADMINISTRATOR(Sets.newHashSet(CRUD_ACCESS));

    private final Set<Permissions> permission;

    Roles(Set<Permissions> permission) {
        this.permission = permission;
    }

    public Set<Permissions> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
