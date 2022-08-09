package com.norab.backstage.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class Admin extends ToJsonString implements UserDetails {
    @Id
    @JsonIgnore
    private UUID adminId;
    private String adminName;
    private String email;
    private String password;
    private String phone;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public Admin() {
    }

    public Admin(String adminName,
                 String password,
                 Set<? extends GrantedAuthority> grantedAuthorities,
                 boolean isAccountNonExpired,
                 boolean isAccountNonLocked,
                 boolean isCredentialsNonExpired,
                 boolean isEnabled) {
        this.adminName = adminName;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Admin(String adminName,
                 String email,
                 String password,
                 String phone,
                 Set<? extends GrantedAuthority> grantedAuthorities,
                 boolean isAccountNonExpired,
                 boolean isAccountNonLocked,
                 boolean isCredentialsNonExpired,
                 boolean isEnabled) {
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Admin(UUID adminId,
                 String adminName,
                 String email,
                 String password,
                 String phone,
                 Set<? extends GrantedAuthority> grantedAuthorities,
                 boolean isAccountNonExpired,
                 boolean isAccountNonLocked,
                 boolean isCredentialsNonExpired,
                 boolean isEnabled) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {

        this.password = new BCryptPasswordEncoder().encode(password);
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
