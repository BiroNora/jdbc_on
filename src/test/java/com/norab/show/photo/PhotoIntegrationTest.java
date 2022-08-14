package com.norab.show.photo;

import com.cedarsoftware.util.io.JsonReader;
import com.norab.security.Permissions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhotoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private GrantedAuthority writeGrant;

    @BeforeEach
    public void setup() {
        writeGrant = new SimpleGrantedAuthority(Permissions.SHOW_WRITE.getPermission());
    }

    @Test
    public void listAllPhotos() throws Exception {
        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("/4.jpg")));
    }

    @Test
    void getPhotoByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/photos/4"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("/images/4.jpg")));
    }

    @Test
    void getPhotoByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/77456"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    void updatePhoto() throws Exception {
        long photo_id = insertPhoto("https://github.com/hard-loan", 1, null, null);
        String url = "/api/v1/photos/" + photo_id;
        Photo p = new Photo("https://github.com/soft-loan", 1, 1, null);

        mockMvc.perform(put(url)
                .with(user("user").authorities(writeGrant))
                .content(p.jsonString())
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("soft-loan")));
    }

    @Test
    void insertPhotoTest() throws Exception {
        Photo p = new Photo("http", null, null, 1);
        mockMvc.perform(post("/api/v1/photos")
                .with(user("user").authorities(writeGrant))
                .content(p.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.photo_id").exists())
            .andExpect(jsonPath("$.photo_id").isNumber());
    }

    @Test
    void insertPhotoByValidId_WithNotExistingMovieId() throws Exception {
        String data = """
            {
            "photoUrl": "https://github.com/BiroNora/JDBC_template_Flyway",
            "movieId": 100
            }
            """;
        mockMvc.perform(post("/api/v1/photos")
                .with(user("user").authorities(writeGrant))
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void insertPhotoByValidId_WithNotExistingActorId() throws Exception {
        String data = """
            {
            "photoUrl": "https://github.com/BiroNora/JDBC_template",
            "actorId": 100
            }
            """;
        mockMvc.perform(post("/api/v1/photos")
                .with(user("user").authorities(writeGrant))
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void insertPhotoByValidId_WithNotExistingRoleId() throws Exception {
        String data = """
            {
            "photoUrl": "https://github.com/BiroNora/JDBC",
            "roleId": 100
            }
            """;
        mockMvc.perform(post("/api/v1/photos")
                .with(user("user").authorities(writeGrant))
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void deletePhotoByValidId() throws Exception {
        long photo_id = insertPhoto("/valami.jpg", null, 2, null);
        mockMvc.perform(delete("/api/v1/photos/" + photo_id)
                .with(user("user").authorities(writeGrant)))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("/valami.jpg"))))
            .andExpect(content().string(containsString("/4.jpg")));
    }

    @Test
    void deletePhotoByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/photos/32254")
                .with(user("user").authorities(writeGrant)))
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    Long insertPhoto(String url, Integer movieId, Integer actorId, Integer roleId) throws Exception {
        Photo p = new Photo(url, movieId, actorId, roleId);
        MvcResult result = mockMvc.perform(post("/api/v1/photos")
                .with(user("user").authorities(writeGrant))
                .content(p.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.photo_id").exists())
            .andExpect(jsonPath("$.photo_id").isNumber())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        Map contentMap = JsonReader.jsonToMaps(content);
        return (Long) contentMap.get("photo_id");
    }
}
