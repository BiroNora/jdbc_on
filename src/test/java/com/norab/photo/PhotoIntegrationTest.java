package com.norab.photo;

import com.cedarsoftware.util.io.JsonReader;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhotoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

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
        String data = """
            {
            "photoUrl": "https://github.com/babo/hard-loan",
            "movieId": 1
            }
            """;
        mockMvc.perform(post("/api/v1/photos")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        String data1 = """
            {
            "photoUrl": "https://github.com/babo/hard-loan",
            "actorId": 1
            }
            """;
        mockMvc.perform(put("/api/v1/photos/3")
                .content(data1)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/photos/3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("hard")));
    }

    @Test
    void insertPhotoTest() throws Exception {
        Photo p = new Photo("http", null, null, 1);
        mockMvc.perform(post("/api/v1/photos")
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
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void deletePhotoByValidId() throws Exception {
        long photo_id = insertPhoto("/valami.jpg", null, 2, null);
        mockMvc.perform(delete("/api/v1/photos/" + photo_id))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("/valami.jpg"))))
            .andExpect(content().string(containsString("/4.jpg")));
    }

    @Test
    void deletePhotoByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/photos/32254"))
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    Long insertPhoto(String url, Integer movieId, Integer actorId, Integer roleId) throws Exception {
        Photo p = new Photo(url, movieId, actorId, roleId);
        MvcResult result = mockMvc.perform(post("/api/v1/photos")
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
