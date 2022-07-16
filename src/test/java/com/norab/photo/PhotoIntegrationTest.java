package com.norab.photo;

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
public class PhotoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void listAllPhotos() throws Exception {
        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("/2.jpg")));
    }

    @Test
    @Order(2)
    void getPhotoByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/photos/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("movie/images/2.jpg")));
    }

    @Test
    @Order(3)
    void getPhotoByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/77456"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(4)
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
    @Order(5)
    void insertPhoto() throws Exception {
        String data = """
            {
            "photoUrl": "https://github.com/BiroNora/JDBC_template_Flyway_SpringBoot",
            "roleId": 1
            }
            """;
        mockMvc.perform(post("/api/v1/photos")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(5)
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
    @Order(6)
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
    @Order(7)
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
    @Order(8)
    void deletePhotoByValidId_WithReference() throws Exception {
        mockMvc.perform(delete("/api/v1/photos/1"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("/1.jpg"))))
            .andExpect(content().string((containsString("/2.jpg"))));
    }

    @Test
    @Order(9)
    void deletePhotoByValidId_WithNoReference() throws Exception {
        mockMvc.perform(delete("/api/v1/photos/3"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("/3.jpg"))))
            .andExpect(content().string((containsString("/4.jpg"))));
    }

    @Test
    @Order(10)
    void deletePhotoByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/photos/32254"))
            .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/v1/photos"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
