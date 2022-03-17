package com.example.taskboard.entity.users;

import com.example.taskboard.entity.classifier.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    @NotNull
    private Long userId;

    @Column(name = "user_role", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "user_login", nullable = false, unique = true)
    @NotNull
    private String userLogin;
    @Column(name = "user_passwd", nullable = false)
    @NotNull
    private String userPasswd;
    @Column(name = "user_mark", nullable = false)
    @NotNull
    private String userMark;

    public Users() {
    }

    public Users(UserRole userRole, String userLogin, String userPasswd) {
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
    }

    public Users(UserRole userRole, String userLogin, String userPasswd, String userMark) {
        this.userRole = userRole;
        this.userLogin = userLogin;
        this.userPasswd = userPasswd;
        this.userMark = userMark;
    }

    public Long getUserId() {
        return userId;
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

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (!userId.equals(users.userId)) return false;
        return getUserLogin().equals(users.getUserLogin());
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + getUserLogin().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userRole=" + userRole +
                ", userLogin='" + userLogin + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userMark='" + userMark + '\'' +
                '}';
    }
}
