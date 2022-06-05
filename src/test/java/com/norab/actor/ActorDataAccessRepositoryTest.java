package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ActorDataAccessRepositoryTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void selectActors() {
        ActorDataAccessRepository aDAR = new ActorDataAccessRepository(jdbcTemplate);
        //ReflectionTestUtils.getField(aDAR, );
        Mockito.when(jdbcTemplate.queryForObject("""
                SELECT actor_id, full_name, birth_date, death_date
                            FROM actor
                            LIMIT 10;
                """, Integer.class
        )).thenReturn(8);
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
