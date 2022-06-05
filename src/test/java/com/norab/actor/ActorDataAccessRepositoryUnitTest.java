package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Sql({"schema.sql", "test-data.sql"})
public class ActorDataAccessRepositoryUnitTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Test
    void selectActors() {
        ActorDataAccessRepository aDAR = new ActorDataAccessRepository(jdbcTemplate);

        assertEquals(8, aDAR.selectActors().size());
    }

    /*@Test
    void selectActorByValidId() {
        Optional<Actor> actor = aDAR.selectActorById(6L);
        assertTrue(actor.isPresent());
    }

    @Test
    void selectActorByInvalidId() {
        Optional<Actor> actor = aDAR.selectActorById(99L);
        assertFalse(actor.isPresent());
    }

    @Test
    void insertActor() {
        Actor actor = new Actor(14L, "Test_nameEE", LocalDate.parse("2222-05-01"), LocalDate.parse(""));
        aDAR.insertActor(actor);
        
        List<Actor> actors = aDAR.selectActors();
        assertEquals(10, actors.size());
        assertEquals("Test_nameEE", actors.get(9).getFullName());
        assertEquals(LocalDate.parse("2222-05-01"), actors.get(9).getBirthDate());
    }

    @Test
    void updateActor() {
        Actor actor = aDAR.selectActorById(9L).get();
        Actor actor1 = new Actor(9L, "Test_namesD", LocalDate.parse("2222-05-01"), LocalDate.parse(""));
        aDAR.updateActor(9L, actor1);

        Actor updated = aDAR.selectActorById(9L).get();
        assertEquals("Test_namesD", updated.getFullName());
    }

    @Test
    void deleteActor() {
        aDAR.deleteActor(14L);

        List<Actor> actors = aDAR.selectActors();
        assertEquals(6, actors.size());
    }*/

}
