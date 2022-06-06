package com.norab.actor;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ActorDataAccessRepositoryUnitTestJupiter {
    @MockBean
    ActorDataAccessRepository aDAR;

    @Test
    void selectActors() {
        when(aDAR.selectActors()).thenReturn(Stream
                .of(new Actor("Greg Kinnear",
                        LocalDate.of(1963, Month.AUGUST, 17),
                        LocalDate.of(1963, Month.AUGUST, 17)),
                    new Actor("Greg Kinnear",
                        LocalDate.of(1963, Month.AUGUST, 17),
                        LocalDate.of(1963, Month.AUGUST, 17))
            ).collect(Collectors.toList()));

        assertEquals(2, aDAR.selectActors().size());
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
