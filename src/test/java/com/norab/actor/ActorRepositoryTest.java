package com.norab.actor;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class ActorRepositoryTest {
    @Autowired
    private ActorRepository repository;

    @Test
    @Order(1)
    void insertLivingActorByValidId() {
        Actor actor = new Actor("Helen Hunt",
            LocalDate.of(1963, Month.JUNE, 15));
        try {
            long id = repository.insertActor(actor);
            var actor1 = repository.selectActorById(id);
            assertTrue(actor1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Actor> expected = repository.selectActors();

        assertNotNull(expected);
        for (Actor a : expected) {
            System.out.println(a.getId());
            System.out.println(a.getFullName());
        }
        assertTrue(expected.size() != 0);
    }

    @Test
    @Order(2)
    void insertDeadActorByValidId() {
        Actor actor = new Actor("Marlon Brando",
            LocalDate.of(1924, Month.APRIL, 3),
            LocalDate.of(2004, Month.JULY, 1));
        try {
            long id = repository.insertActor(actor);
            var actor1 = repository.selectActorById(id);
            assertTrue(actor1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Actor> expected = repository.selectActors();

        assertNotNull(expected);
        for (Actor a : expected) {
            System.out.println(a.getId());
            System.out.println(a.getFullName());
        }
        assertTrue(expected.size() != 0);
    }

    @Test
    @Order(3)
    void getExistingActor() {
        Long id = 2L;
        try {
            var actor1 = repository.selectActorById(id);
            assertTrue(actor1.isPresent());
            assertEquals("Alan Arkin", actor1.get().getFullName());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void getNotExistingActor() throws Exception {

    }

    /*@Test
    void selectActorById() {
        Long id = 17L;
        Actor actor = new Actor(17L, "John Wick",
            LocalDate.of(2000, Month.DECEMBER, 12),
            LocalDate.of(2003, Month.FEBRUARY, 10));

        repository.insertActor(actor);
        Optional<Actor> expected = repository.selectActorById(id);

        assertThat(expected).isPresent();

    }*/
    /*@Test
    void allMoviesByActor() {
    }

    @Test
    void selectActors() {

    }

    @Test
    void insertActor() {
    }

    @Test
    void deleteActor() {
    }



    @Test
    void updateActor() {
    }*/
}