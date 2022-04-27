package com.example.taskboard.entity.classifier;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Set.of(Permission.USER_MODIFY, Permission.USER_DELETE, Permission.USER_WRITE, Permission.USER_READ)),
    MANAGER(Set.of(Permission.USER_MODIFY, Permission.USER_WRITE, Permission.USER_READ)),
    USER(Set.of(Permission.USER_MODIFY, Permission.USER_READ)),
    UNKNOWN(Set.of(Permission.USER_FORBIDDEN));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities (){
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
