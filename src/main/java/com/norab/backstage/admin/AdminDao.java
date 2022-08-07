package com.norab.backstage.admin;

import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface AdminDao<Admin> {

    List<String> listAdmins();

    int insertAdmin(Admin admin) throws IllegalStateException;

    DeleteResult deleteAdmin(UUID adminId, boolean force);

    Optional<Admin> selectAdminById(UUID adminId);

    List<Admin> selectAdminByName(String name, boolean match);

    int updateAdmin(UUID adminId, Admin admin);
}
