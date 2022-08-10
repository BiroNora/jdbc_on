package com.norab.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.norab.security.Permissions.*;

public enum Roles {
    USER(Sets.newHashSet(SHOW_READ, OPINION_WRITE, OPINION_DELETE)),
    STAFF(Sets.newHashSet(SHOW_READ, SHOW_WRITE, OPINION_DELETE)),
    HR(Sets.newHashSet(STAFF_ACCESS));

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
