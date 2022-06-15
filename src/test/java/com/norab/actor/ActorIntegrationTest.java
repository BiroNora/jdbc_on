package com.norab.actor;

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
public class ActorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void listAllActors() throws Exception {
        mockMvc.perform(get("/api/v1/actors"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("John")));
    }

    @Test
    @Order(2)
    void getExistingActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Alan")));
    }

    @Test
    @Order(3)
    void getNotExistingActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/123456"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(4)
    void getMoviesByActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Carib")));
    }

    @Test
    @Order(5)
    void updateActor() throws Exception {
        String data = """
            {            
            "fullName": "Haumann Péter",
            "birthDate": "1941-05-17"
            }
            """;
        mockMvc.perform(post("/api/v1/actors")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        String data1 = """
            {            
            "fullName": "Haumann Péter",
            "birthDate": "1941-05-17",
            "deathDate": "2022-05-28"
            }
            """;
        mockMvc.perform(put("/api/v1/actors/3")
                .content(data1)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors/3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("2022")));
    }

    @Test
    @Order(6)
    void insertActor() throws Exception {
        String data = """
            {            
            "fullName": "Fedák Sári",
            "birthDate": "1879-09-27"
            }
            """;
        mockMvc.perform(post("/api/v1/actors")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void deleteExistingActorWithNoReferenceConflict() throws Exception {
        mockMvc.perform(delete("/api/v1/actors/2"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("1934"))));
    }

    @Test
    @Order(8)
    void deleteExistingActorWithReferenceConflict() throws Exception {
        mockMvc.perform(delete("/api/v1/actors/1"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("1963"))));
    }

    @Test
    @Order(9)
    void deleteNotExistingActor() throws Exception {
        mockMvc.perform(delete("/api/v1/actors/2222"))
            .andExpect(status().is4xxClientError());
    }

}
