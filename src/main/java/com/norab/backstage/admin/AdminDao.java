package com.norab.backstage.admin;

import com.norab.utils.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface AdminDao<Admin> {

    List<Admin> listAdmins(Page page);

    Optional<Admin> selectAdminById(UUID adminId);

    List<Admin> selectAdminByName(String name, boolean match);

    Admin adminByName(String name) throws UsernameNotFoundException;

    int updateAdmin(UUID adminId, Admin admin);

    String insertAdmin(Admin admin) throws IllegalStateException;

    int deleteAdmin(UUID adminId);
}
