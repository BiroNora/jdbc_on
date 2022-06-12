package com.norab.actor;

import org.junit.jupiter.api.Test;
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

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ActorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listAllActors() throws Exception {
        mockMvc.perform(get("/api/v1/actors"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Jonathan")));
    }

    @Test
    void getExistingActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Jonathan")));
    }

    @Test
    void getNotExistingActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/123456"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    void getMoviesByActor() throws Exception {
        mockMvc.perform(get("/api/v1/actors/1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Carib")));
    }

    @Test
    void insertActor() throws Exception {
        String data = """
            {            
            "fullName": "Fedák Sárika",
            "birthDate": "1879-09-27"
            }
            """;
        mockMvc.perform(post("/api/v1/actors")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());
    }

    @Test
    void updateActor() throws Exception {
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

        String data1 = """
            {            
            "fullName": "Fedák Sári",
            "birthDate": "1879-09-27",
            "deathDate": "1955-05-05"
            }
            """;
        mockMvc.perform(put("/api/v1/actors/2")
                .content(data1)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("1955")));
    }

    @Test
    void deleteExistingActor() throws Exception {
        String data = """
            {            
            "fullName": "Fedák Sári",
            "birthDate": "1879-09-27",
            "deathDate": "1955-05-05"
            }
            """;
        mockMvc.perform(post("/api/v1/actors")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(delete("/api/v1/actors/2"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("1955"))));
    }

    @Test
    void deleteNotExistingActor() throws Exception {
        mockMvc.perform(delete("/api/v1/actors/2222"))
            .andExpect(status().is4xxClientError());
    }

}
