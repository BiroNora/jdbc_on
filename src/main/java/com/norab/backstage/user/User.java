package com.norab.backstage.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.security.Roles;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class User extends ToJsonString implements UserDetails {
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
    @Id
    private UUID userId;
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private String roles;
    @JsonIgnore
    private Set<GrantedAuthority> grantedAuthorities;
    @JsonIgnore
    private List<String> rolesList;

    public User(String fullName,
                String password,
                String roles) throws IllegalArgumentException {
        if (fullName == null || fullName.isBlank()
            || password == null || password.isBlank() || password.length() < 4
            || email == null || email.isBlank() || email.length() < 6
            || roles == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;

        setUpRoles(roles);
    }

    public User(UUID userId,
                String fullName,
                String email,
                String password,
                String phone,
                String roles,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) throws IllegalArgumentException {
        if (fullName == null || fullName.isBlank()
            || password == null || password.isBlank() || password.length() < 4
            || email == null || email.isBlank() || email.length() < 6
            || roles == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;

        setUpRoles(roles);
    }

    private void setUpRoles(String input) throws IllegalArgumentException {
        grantedAuthorities = new HashSet<>();
        rolesList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (String r : input.toUpperCase().split(",")) {
            Roles nr = Roles.valueOf(r.strip());
            if (!sb.isEmpty()) {
                sb.append(',');
            }
            sb.append(nr.name());
            rolesList.add(nr.name());
            grantedAuthorities.addAll(nr.getGrantedAuthorities());
        }
        roles = sb.toString();
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

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public List<String> getRoles() {
        return rolesList;
    }

    @JsonIgnore
    public String getRolesAsString() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String getUsername() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
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
            ", roles=" + roles +
            ", isAccountNonExpired=" + isAccountNonExpired +
            ", isAccountNonLocked=" + isAccountNonLocked +
            ", isCredentialsNonExpired=" + isCredentialsNonExpired +
            ", isEnabled=" + isEnabled +
            '}';
    }
}
