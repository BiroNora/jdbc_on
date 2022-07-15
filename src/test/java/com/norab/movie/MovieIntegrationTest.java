package com.norab.movie;

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
public class MovieIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void listAllMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Caribbean")));
    }

    @Test
    @Order(2)
    void getMovieByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/movies/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Ismeretlen")));
    }

    @Test
    @Order(3)
    void getMovieByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/765432"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(4)
    void updateMovie() throws Exception {
        String data = """
            {
            "title": "Minden végzet nehéz",
            "releaseDate": 2003
            }
            """;
        mockMvc.perform(post("/api/v1/movies")
                .content(data)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        String data1 = """
            {
            "title": "Minden végzet nehéz",
            "titleOriginal": "Something''s Gotta Give",
            "releaseDate": 2003
            }
            """;
        mockMvc.perform(put("/api/v1/movies/3")
                .content(data1)
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies/3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Give")));
    }

    @Test
    @Order(5)
    void insertMovie() throws Exception {
        String data = """
            {
            "title": "A szajré",
            "releaseDate": 2001
            }
            """;
        mockMvc.perform(post("/api/v1/movies")
                .content(data)
                .header("Content-Type", "application/json"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void deleteMovieByValidId_ReferenceConflict() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/1"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Fekete"))))
            .andExpect(content().string((containsString("kicsi"))));
    }

    @Test
    @Order(7)
    void deleteMovieByValidId_NoReferenceConflict() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/2"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Sunshine"))));
    }

    @Test
    @Order(8)
    void deleteMovieByValidId() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/7175"))
            .andExpect(status().is4xxClientError());
    }

}
