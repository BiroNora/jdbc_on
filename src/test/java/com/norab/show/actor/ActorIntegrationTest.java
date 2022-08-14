package com.norab.show.actor;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.norab.security.Permissions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ActorIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private GrantedAuthority writeGrant;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        writeGrant = new SimpleGrantedAuthority(Permissions.SHOW_WRITE.getPermission());
    }

    @Test
    void recordTest() {
        Person a = new Person("Fedák Sári", (short) 1879, (short) 1955);
        String expected = """
            {"fullName":"Fedák Sári","birthDate":1879,"deathDate":1955}
            """.strip();
        assertEquals(expected, a.jsonString());

        Person b = new Person("Blaha Lujza", null, null);
        String expected1 = """
            {"fullName":"Blaha Lujza"}
            """.strip();
        assertEquals(expected1, b.jsonString());
    }

    @Test
    public void listAllActors() throws Exception {
        mockMvc.perform(get("/api/v1/actors?page=1&size=10")
                .with(user("user").authorities(writeGrant)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Donald Sutherland")));
    }

    @Test
    public void listAllActors_DefaultParameters() throws Exception {
        mockMvc.perform(
                get("/api/v1/actors")
                    .with(user("user").password("pass").roles("XXX")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Donald Sutherland")));
    }

    @Test
    public void listAllActorsWithInsert() throws Exception {
        Person a = new Person("Jacky Nichols", (short) 1940);
        mockMvc.perform(post("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
                .content(a.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.actor_id").exists())
            .andExpect(jsonPath("$.actor_id").isNumber());

        mockMvc.perform(get("/api/v1/actors?page=2&size=10")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Jacky Nichols")));
    }

    @Test
    void getActorByValidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/2")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Greg")));
    }

    @Test
    void getActorByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/actors/123456")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    void getMoviesByActor() throws Exception {
        mockMvc.perform(get("/api/v1/crossed/movies/actor/1")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Karib")));
    }

    @Test
    void updateActor() throws Exception {
        Long actorId = insertActor("Haumann Péter", (short) 1941);
        Person actor = new Person("Haumann Péter", (short) 1941, (short) 2022);

        mockMvc.perform(put("/api/v1/actors/" + actorId)
                .with(user("user").authorities(writeGrant))
                .content(actor.jsonString())
                .header("Content-Type", "application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors/" + actorId)
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("2022")));
    }

    @Test
    void insertActorTest() throws Exception {
        Person a = new Person("Fedák Sári", (short) 1879, (short) 1955);
        mockMvc.perform(post("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
                .content(a.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.actor_id").exists())
            .andExpect(jsonPath("$.actor_id").isNumber());
    }

    @Test
    void insertUnknown() throws Exception {
        Person a = new Person("Kiss Manyi", null);
        mockMvc.perform(post("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
                .content(a.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.actor_id").exists())
            .andExpect(jsonPath("$.actor_id").isNumber());
    }

    @Test
    void deleteActorByValidId_NoReferenceConflict() throws Exception {
        Long actorId = insertActor("Dakota Johnson", (short) 1989);
        mockMvc.perform(delete("/api/v1/actors/" + actorId)
                .with(user("user").authorities(writeGrant))
            )
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Dakota Johnson"))));
    }

    @Test
    void deleteActorByValidId_ReferenceConflict() throws Exception {
        Long actorId = insertActor("Melanie Griffith", (short) 1957);
        String actorUrl = "/api/v1/actors/" + actorId;

        Map<String, Object> lp = Map.of("roleName", "LP", "movieId", 3L, "actorId", actorId);
        String roleData = JsonWriter.objectToJson(lp);
        mockMvc.perform(post("/api/v1/roles")
                .with(user("user").authorities(writeGrant))
                .content(roleData)
                .contentType("application/json"))
            .andExpect(status().isOk());

        mockMvc.perform(delete(actorUrl).with(user("user").authorities(writeGrant))
            )
            .andExpect(status().isConflict());

        mockMvc.perform(get("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(delete(actorUrl + "?force=true").with(user("user").authorities(writeGrant))
            )
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/actors").with(user("user").authorities(writeGrant))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("Melanie Griffith"))));
    }

    @Test
    void deleteActorByInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/actors/2222")
                .with(user("user").authorities(writeGrant))
            )
            .andExpect(status().is4xxClientError());
    }

    Long insertActor(String userName, Short birthDate) throws Exception {
        Person a = new Person(userName, birthDate);
        MvcResult result = mockMvc.perform(post("/api/v1/actors")
                .with(user("user").authorities(writeGrant))
                .content(a.jsonString())
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.actor_id").exists())
            .andExpect(jsonPath("$.actor_id").isNumber())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        Map contentMap = JsonReader.jsonToMaps(content);
        return (Long) contentMap.get("actor_id");
    }
}
