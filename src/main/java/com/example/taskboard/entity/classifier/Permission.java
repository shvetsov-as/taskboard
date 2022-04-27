package com.example.taskboard.entity.classifier;

public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_DELETE("user:delete"),
    USER_MODIFY("user:modify"),
    USER_FORBIDDEN("user:forbidden");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
