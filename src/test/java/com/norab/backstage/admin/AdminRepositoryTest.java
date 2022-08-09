package com.norab.backstage.admin;

import com.norab.utils.Page;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")
@SpringBootTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository repository;

    @Test
    void listAdmins() {
        Page page = new Page(1, 10);
        List<Admin> admins = repository.listAdmins(page);
        for (Admin a : admins) {
            System.out.println(a.getAdminId() + " ");
            System.out.println(a.getAdminName());
        }
        assertTrue(admins.size() > 0);
    }

    @Test
    void selectAdminById() {
        Admin admin = new Admin("Gipsz Jakab", "gjaki@gmail.com", "passzW%", "555-222", Set.of(), true, true, true, true);
        String adminId = repository.insertAdmin(admin);
        Optional<Admin> selected = repository.selectAdminById(UUID.fromString(adminId));
        assertTrue(selected.isPresent());
        assertEquals("Gipsz Jakab", selected.get().getAdminName());

        UUID id = UUID.fromString("f1b9ac74-099b-4a21-9a0a-b14f14acddf0");
        Optional<Admin> admin1 = repository.selectAdminById(id);
        assertTrue(admin1.isEmpty());
    }

    @Test
    void selectAdminByName() {
        Admin admin = new Admin("SpiderMan", "speedy@gmail.com", "passz123%", "5557-222", Set.of(), true, true, true, true);
        repository.insertAdmin(admin);
        List<Admin> admins = repository.selectAdminByName(admin.getAdminName(), true);
        assertTrue(admins.size() > 0);

        List<Admin> admins1 = repository.selectAdminByName("00", true);
        assertTrue(admins1.isEmpty());
    }

    @Test
    void updateAdmin() {
        UUID id = UUID.fromString("fbfea6e6-2e5e-4f7d-908b-6cef802b6270");
        Admin admin = repository.selectAdminById(id).orElseThrow();
        admin.setAdminName("Gábor Zsazsa");
        int result = repository.updateAdmin(id, admin);
        assertEquals(1, result);

        Admin admin1 = new Admin("ZZTop", "zzt@gmail.com", "123", "5557-222", Set.of(), true, true, true, true);
        UUID id1 = UUID.fromString("XXXea6e6-2e5e-4f7d-908b-6cef802b6270");
        int result1 = repository.updateAdmin(id1, admin1);
        assertEquals(0, result1);
    }

    @Test
    void insertAdmin() {
        Admin admin = new Admin("Sugar Baby", "sbaby@gmail.com", "123%", "5557-222", Set.of(), true, true, true, true);
        String result = repository.insertAdmin(admin);
        Optional<Admin> admin1 = repository.selectAdminById(UUID.fromString(result));
        assertTrue(admin1.isPresent());
    }

    @Test
    void deleteAdmin() {
        Admin admin = new Admin("Man At Work", "maw@gmail.com", "1234", "5557-222", Set.of(), true, true, true, true);
        String result = repository.insertAdmin(admin);
        Optional<Admin> admin1 = repository.selectAdminById(UUID.fromString(result));
        assertTrue(admin1.isPresent());

        int expected = repository.deleteAdmin(UUID.fromString(result));
        assertEquals(1, expected);
    }
}
