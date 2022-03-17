package com.example.taskboard.entity.users.dto;

import com.example.taskboard.entity.classifier.UserRole;

public class UsersDtoRequest {

    private Long userId;
    private UserRole userRole;
    private String userLogin;
    private String userPasswd;
    private String userPasswd2;
    private String userMark;

    public UsersDtoRequest() {
    }

    public UsersDtoRequest(UserRole userRole, String userLogin, String userPasswd, String userPasswd2) {
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
        this.userPasswd2 = userPasswd2;
    }

    public UsersDtoRequest(Long userId, UserRole userRole, String userLogin, String userPasswd, String userPasswd2) {
        this.userId = userId;
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
        this.userPasswd2 = userPasswd2;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
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
