package com.norab.backstage.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Admin {
    @Id
    @JsonIgnore
    private UUID adminId;
    private String adminName;
    private String email;
    private String password;
    private String phone;

    public Admin() {
    }

    public Admin(String adminName, String email, String password, String phone) {
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Admin(UUID adminId, String adminName, String email, String password, String phone) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Admin{" +
            "adminId=" + adminId +
            ", adminName='" + adminName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            '}';
    }
}
