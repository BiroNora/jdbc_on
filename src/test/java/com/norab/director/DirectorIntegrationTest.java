package com.norab.director;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DirectorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    String url = "/api/v1/directors";

    @Test
    void listDirectors() throws Exception {
        mockMvc.perform(get(url))
            .andExpect(status().isOk());
    }

    @Test
    void listDirectorsAndMovies() throws Exception {
        String path = url + "/all";
        mockMvc.perform(get(path))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Gore Verbinski")));
    }

    @Test
    void selectDirectorById() throws Exception {
        String path = url + "/exists?actorid=5&movieid=4";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void selectDirectorByInvalidId() throws Exception {
        String path = url + "/exists?actorid=1&movieid=7";
        mockMvc.perform(get(path))
            .andExpect(jsonPath("$.is_valid").exists())
            .andExpect(jsonPath("$.is_valid").isBoolean())
            .andExpect(jsonPath("$.is_valid").value(false));
    }

    @Test
    void selectMoviesByDirector() throws Exception {
        String path = url + "/exists?actorid=8&movieid=1";
        mockMvc.perform(get(path))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.is_valid").exists())
            .andExpect(jsonPath("$.is_valid").isBoolean())
            .andExpect(jsonPath("$.is_valid").value(true));
    }

    @Test
    void selectDirectorsByMovieTitle() throws Exception {
        String path = url + "/dirbymovie?title=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void insertDirector() throws Exception {
        String d = new Director(1, 1).jsonString();
        mockMvc.perform(post("/api/v1/directors")
                .content(d)
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").isBoolean());
    }

    @Test
    void deleteDirectorByValidValues() throws Exception {
        String d = new Director(1, 1).jsonString();

        mockMvc.perform(post("/api/v1/directors")
                .content(d)
                .contentType("application/json"))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(delete("/api/v1/directors/1/1"))
            .andExpect(status().isOk());
    }

    @Test
    void deleteDirectorByInvalidValues() throws Exception {
        mockMvc.perform(delete("/api/v1/directors/100/101"))
            .andExpect(status().isNotFound());
    }
}
