package com.norab.backstage.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

public class User extends ToJsonString {
    @Id
    @JsonIgnore
    private UUID userId;
    private String userName;
    private String email;
    private String password;
    private List<Article> articles;

    public User() {
    }

    public User(String userName, String email, String password, List<Article> articles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.articles = articles;
    }

    public User(UUID userId, String userName, String email, String password, List<Article> articles) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.articles = articles;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", articles=" + articles +
            '}';
    }
}
