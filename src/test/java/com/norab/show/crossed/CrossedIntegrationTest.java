package com.norab.show.crossed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CrossedIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    String url = "/api/v1/crossed";

    @Test
    void allMoviesByActorById() throws Exception {
        String path = url + "/movies/actor/1";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allMoviesByReleaseDateAsc() throws Exception {
        String path = url + "/movies/date";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void searchByMovieTitle() throws Exception {
        String path = url + "/movies?title=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void searchByActorBirthDate() throws Exception {
        String path = url + "/actors/date/1963";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allActorsByMovie() throws Exception {
        String path = url + "/actorsbymovie?title=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allPlaysByActor() throws Exception {
        String path = url + "/playsbyactor?name=ush";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allMoviesByActorByName() throws Exception {
        String path = url + "/moviesbyactor?name=ush";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allMoviesAndPlaysByActor() throws Exception {
        String path = url + "/moviesplaysbyactor?name=epp";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allPlaysAndActorsByMovie() throws Exception {
        String path = url + "/playsactorsbymovie?title=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allPhotosByActor() throws Exception {
        String path = url + "/photosbyactor?name=ush";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allPhotosByPlays() throws Exception {
        String path = url + "/photosbyrole?name=boss";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void allPhotosByMovie() throws Exception {
        String path = url + "/photosbymovie?name=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void movieSpecification() throws Exception {
        String path = url + "/moviespec?name=kar";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void genresPerActor() throws Exception {
        String path = url + "/actgenres?name=ush";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }

    @Test
    void genresPerDirector() throws Exception {
        String path = url + "/dirgenres?name=epp";
        mockMvc.perform(get(path))
            .andExpect(status().isOk());
    }
}
