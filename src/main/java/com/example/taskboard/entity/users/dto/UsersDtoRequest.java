package com.example.taskboard.entity.users.dto;

import com.example.taskboard.entity.classifier.UserRole;
import com.example.taskboard.entity.classifier.UserStatus;

import java.util.UUID;

public class UsersDtoRequest {

    private UUID userId;
    private UserRole userRole;
    private String userLogin;
    private String userPasswd;
    private String userPasswd2;
    private UserStatus userStatus;

    public UsersDtoRequest() {
    }

    public UsersDtoRequest(UUID userId, UserRole userRole, String userLogin, String userPasswd, String userPasswd2, UserStatus userStatus) {
        this.userId = userId;
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
        this.userPasswd2 = userPasswd2;
        this.userStatus = userStatus;
    }

    public UsersDtoRequest(UserRole userRole, String userLogin, String userPasswd, String userPasswd2, UserStatus userStatus) {
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
        this.userPasswd2 = userPasswd2;
        this.userStatus = userStatus;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserPasswd2() {
        return userPasswd2;
    }

    public void setUserPasswd2(String userPasswd2) {
        this.userPasswd2 = userPasswd2;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDtoRequest)) return false;

        UsersDtoRequest that = (UsersDtoRequest) o;

        return getUserLogin().equals(that.getUserLogin());
    }

    @Override
    public int hashCode() {
        return getUserLogin().hashCode();
    }

    @Override
    public String toString() {
        return "UsersDtoRequest{" +
                "userRole=" + userRole +
                ", userLogin='" + userLogin + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                '}';
    }
}
