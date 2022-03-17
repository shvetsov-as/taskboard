package com.example.taskboard.entity.users.dto;
import com.example.taskboard.entity.classifier.UserRole;

public class UsersDtoResponse {

    private Long userId;
    private UserRole userRole;
    private String userLogin;

    public UsersDtoResponse() {
    }

    public UsersDtoResponse(Long userId, UserRole userRole, String userLogin) {
        this.userId = userId;
        this.userRole = userRole;
        this.userLogin = userLogin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDtoResponse)) return false;

        UsersDtoResponse that = (UsersDtoResponse) o;

        if (!getUserId().equals(that.getUserId())) return false;
        return getUserLogin().equals(that.getUserLogin());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUserLogin().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UsersDtoResponse{" +
                "userId=" + userId +
                ", userRole=" + userRole +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
