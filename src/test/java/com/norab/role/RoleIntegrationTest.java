package com.norab.role;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RoleIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void listAllRoles() throws Exception {
        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Jimmy")));
    }

    @Test
    @Order(2)
    void getRoleByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/roles/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("oove")));
    }

    @Test
    @Order(3)
    void getRoleByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/roles/771256"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(4)
    void updateRole() throws Exception {
        String data = """
            {            
            "roleName": "Barbossa",
            "movieId": 1
            }
            """;
        mockMvc.perform(post("/api/v1/roles")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        String data1 = """
            {            
            "roleName": "Barbossa",
            "movieId": 1,
            "actorId": 3
            }
            """;
        mockMvc.perform(put("/api/v1/roles/4")
                .content(data1)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/roles/4"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("bossa")));
    }

    @Test
    @Order(5)
    void insertRole() throws Exception {
        String data = """
            {            
            "roleName": "Norrington",
            "movieId": 1
            }
            """;
        mockMvc.perform(post("/api/v1/roles")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void insertRoleWithNotExistingMovieId() throws Exception {
        String data = """
            {            
            "roleName": "Murtogg",
            "movieId": 100
            }
            """;
        mockMvc.perform(post("/api/v1/roles")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(7)
    void insertRoleWithNotExistingActorId() throws Exception {
        String data = """
            {            
            "roleName": "Cotton",
            "actorId": 1001
            }
            """;
        mockMvc.perform(post("/api/v1/roles")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(8)
    void deleteRoleByValidId_WithReference() throws Exception {
        mockMvc.perform(delete("/api/v1/roles/1"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Jack"))))
            .andExpect(content().string((containsString("McGinty"))));
    }

    @Test
    @Order(9)
    void deleteRoleByValidId_WithNoReference() throws Exception {
        mockMvc.perform(delete("/api/v1/roles/3"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("McGinty"))))
            .andExpect(content().string((containsString("oov"))));
    }

    @Test
    @Order(10)
    void deleteRoleByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/roles/33333"))
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
