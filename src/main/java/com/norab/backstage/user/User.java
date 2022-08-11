package com.norab.backstage.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.security.Roles;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class User extends ToJsonString implements UserDetails {
    @Id
    @JsonIgnore
    private UUID userId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private List<Roles> role;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User() {
    }

    public User(String fullName,
                String password,
                Set<? extends GrantedAuthority> grantedAuthorities,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.fullName = fullName;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User(String fullName,
                String email,
                String password,
                String phone,
                Set<? extends GrantedAuthority> grantedAuthorities,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User(UUID userId,
                String fullName,
                String email,
                String password,
                String phone,
                List<Roles> role,
                Set<? extends GrantedAuthority> grantedAuthorities,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String userName) {
        this.fullName = userName;
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

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role) {
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            '}';
    }
}
