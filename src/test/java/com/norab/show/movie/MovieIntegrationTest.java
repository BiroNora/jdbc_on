package com.norab.show.movie;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
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
public class MovieIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listAllMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Caribbean")));
    }

    @Test
    void getMovieByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/movies/2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Ismeretlen")));
    }

    @Test
    void getMovieByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/765432"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    /*@Test
    void updateMovie1() throws Exception {
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
*/
    @Test
    void updateMovie() throws Exception {
        Long id = insertMovie("Minden végzet nehéz", (short) 2003);
        Movie movie = new Movie("Minden végzet nehéz", "Something''s Gotta Give", (short) 2003);

        mockMvc.perform(put("/api/v1/movies/" + id)
                .content(movie.jsonString())
                .header("Content-Type", "application/json"))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies/" + id))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Give")));
    }

    @Test
    void insertMovie() throws Exception {
        String s = new Movie("A szajré", "The Score", (short) 2001).jsonString();

        mockMvc.perform(post("/api/v1/movies")
                .content(s)
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.movie_id").exists())
            .andExpect(jsonPath("$.movie_id").isNumber());
    }

    @Test
    void recordTest() {
        Movie m = new Movie("Szemtől szemben", "Szemtől szemben", (short) 1984);
        String expected = """
            {"title":"Szemtől szemben","titleOriginal":"Szemtől szemben","releaseDate":1984,"isAdult":false}
            """.strip();
        assertEquals(expected, m.jsonString());
    }

    @Test
    void deleteMovieByValidId_ReferenceConflict() throws Exception {
        String title = "A mumia";
        Long movieId = insertMovie(title, (short) 1984);
        String movieUrl = "/api/v1/movies/" + movieId;
        //TODO: UTF-8

        Map<String, Object> lp = Map.of("roleName", "LP", "movieId", movieId, "actorId", 4L);
        String roleData = JsonWriter.objectToJson(lp);

        mockMvc.perform(post("/api/v1/roles")
                .content(roleData)
                .contentType("application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(delete(movieUrl))
            .andExpect(status().isConflict());

        mockMvc.perform(get("/api/v1/movies"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(title)));

        mockMvc.perform(delete(movieUrl + "?force=true"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString(title))));
    }

    @Test
    void deleteMovieByValidId_NoReferenceConflict() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/6"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/movies"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("bella"))));
    }

    @Test
    void deleteMovieByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/7175"))
            .andExpect(status().is4xxClientError());
    }

    Long insertMovie(String title, Short releaseDate) throws Exception {
        Movie m = new Movie(title, title, releaseDate);
        MvcResult result = mockMvc.perform(post("/api/v1/movies")
                .content(m.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.movie_id").exists())
            .andExpect(jsonPath("$.movie_id").isNumber())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        Map contentMap = JsonReader.jsonToMaps(content);
        return (Long) contentMap.get("movie_id");
    }

}
