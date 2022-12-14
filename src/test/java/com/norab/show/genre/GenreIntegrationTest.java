package com.norab.show.genre;

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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GenreIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private GrantedAuthority writeGrant;

    @BeforeEach
    public void setup() {
        writeGrant = new SimpleGrantedAuthority(Permissions.SHOW_WRITE.getPermission());
    }

    @Test
    public void selectGenres() throws Exception {
        mockMvc.perform(get("/api/v1/genres"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("med")));
    }

    @Test
    public void selectAllGenre() throws Exception {
        mockMvc.perform(get("/api/v1/genres/all"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("ance")));
    }

    @Test
    public void selectGenreById() throws Exception {
        mockMvc.perform(get("/api/v1/genres/id")
                .param("id", String.valueOf(5))
                .param("genre", "crime"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void selectGenresByMovieId() throws Exception {
        mockMvc.perform(get("/api/v1/genres/genre/3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("ance")));
    }

    @Test
    public void selectMoviesByGenre() throws Exception {
        mockMvc.perform(get("/api/v1/genres/genre")
                .param("genre", "crime"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("enk")));
    }

    @Test
    public void invalidLocation() throws Exception {
        mockMvc.perform(get("/api/v1/genres/filmgen")
                .param("title", "ossz")
                .param("location", "rabbit"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void selectGenresByMovieTitle() throws Exception {
        mockMvc.perform(get("/api/v1/genres/filmgen")
                .param("title", "ossz")
                .param("location", "TITLE"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("dra")));
    }

    @Test
    public void selectGenresByMovieOriginaltitle() throws Exception {
        mockMvc.perform(get("/api/v1/genres/filmgen")
                .param("title", "Gift")
                .param("location", "ORIGTITLE"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("dra")));
    }

    @Test
    public void selectGenresByMovieTitle_Originaltitle() throws Exception {
        mockMvc.perform(get("/api/v1/genres/filmgen")
                .param("title", "Gift")
                .param("location", "ALL"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("dra")));
    }

    @Test
    public void selectGenresByMovieDefault() throws Exception {
        mockMvc.perform(get("/api/v1/genres/filmgen")
                .param("title", "The Gift"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("dra")));
    }

    @Test
    void insertGenre() throws Exception {
        String data = """
            {
            "movieId": 2,
            "genre": "success"
            }
            """;
        mockMvc.perform(post("/api/v1/genres")
                .with(user("user").authorities(writeGrant))
                .content(data)
                .contentType("application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/genres")
                .with(user("user").authorities(writeGrant))
                .content(data)
                .contentType("application/json"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteGenreByValidValues() throws Exception {
        String g = new Genre(1, "dramatic").jsonString();
        mockMvc.perform(post("/api/v1/genres")
                .with(user("user").authorities(writeGrant))
                .content(g)
                .contentType("application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(delete("/api/v1/genres/1?genre=dramatic")
                .with(user("user").authorities(writeGrant)))
            .andExpect(status().isOk());
    }

    @Test
    void deleteGenreByInvalidValues() throws Exception {
        mockMvc.perform(delete("/api/v1/genres/100?genre=dramatic")
                .with(user("user").authorities(writeGrant)))
            .andExpect(status().isNotFound());
    }
}
