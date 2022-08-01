package com.norab.role;

import com.cedarsoftware.util.io.JsonReader;
import com.norab.photo.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RoleIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listAllRolesDefault() throws Exception {
        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Barbossa")));
    }

    @Test
    public void listAllRoles() throws Exception {
        mockMvc.perform(get("/api/v1/roles?page=2&size=10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Jack Sparrow")));
    }

    @Test
    void getRoleByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/roles/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Billy Whistler")));
    }

    @Test
    void getRoleByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/roles/771256"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    void updateRole() throws Exception {
        Long role_id = insertRole("Pink Panther", 4, 1);
        String url = "/api/v1/roles/" + role_id;

        Plays play = new Plays("Purple Panther", 4, 3);
        mockMvc.perform(put(url)
                .content(play.jsonString())
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Purple Panther")));
    }

    @Test
    void testOfJson() {
        Plays p = new Plays("Norrington", 1, null);
        String exp = "{\"roleName\":\"Norrington\",\"movieId\":1}";
        assertEquals(exp, p.jsonString());
    }

    @Test
    void insertRoleTest() throws Exception {
        Plays p = new Plays("Norrington", 1, 1);
        mockMvc.perform(post("/api/v1/roles")
                .content(p.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.role_id").exists())
            .andExpect(jsonPath("$.role_id").isNumber());
    }

    @Test
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
    void deleteRoleByValidId_WithReference() throws Exception {
        long role_id = insertRole("Norfolk Earl", 2, 5);
        Photo p = new Photo("https", 2, 5, (int) role_id);

        mockMvc.perform(post("/api/v1/photos")
                .content(p.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(delete("/api/v1/roles/" + role_id))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Norfolk Earl"))));
    }

    @Test
    void deleteRoleByValidId_WithNoReference() throws Exception {
        Long role_id = insertRole("Norfolk Earl", 2, 5);
        mockMvc.perform(delete("/api/v1/roles/" + role_id))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Norfolk Earl"))));
    }

    @Test
    void deleteRoleByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/roles/33333"))
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/v1/roles"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    Long insertRole(String roleName, Integer movieId, Integer actorId) throws Exception {
        Plays p = new Plays(roleName, movieId, actorId);
        MvcResult result = mockMvc.perform(post("/api/v1/roles")
                .content(p.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.role_id").exists())
            .andExpect(jsonPath("$.role_id").isNumber())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        Map contentMap = JsonReader.jsonToMaps(content);
        return (Long) contentMap.get("role_id");
    }
}
