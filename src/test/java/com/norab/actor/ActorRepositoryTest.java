package com.norab.actor;

import com.norab.movie.Movie;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
class ActorRepositoryTest {
    @Autowired
    private ActorRepository repository;



    @Test
    @Order(2)
    void selectActors() {
        List<Actor> actors = repository.selectActors();
        for (Actor a : actors) {
            System.out.print(a.getId() + " ");
            System.out.println(a.getFullName());
        }
        assertEquals(actors.size(), 3);
        assertEquals(actors.get(2).getFullName(), "Geoffry Rush");
    }

    @Test
    @Order(3)
    void insertLiving_DeceasedActor() {
        Actor actorLiv = new Actor("Helen Hunt",
            LocalDate.of(1963, Month.JUNE, 15));
        Actor actorDec = new Actor("Marlon Brando",
            LocalDate.of(1924, Month.APRIL, 3),
            LocalDate.of(2004, Month.JULY, 1));

        long idLiv = repository.insertActor(actorLiv);
        var actorLiv1 = repository.selectActorById(idLiv);
        assertTrue(actorLiv1.isPresent());
        assertEquals(actorLiv1.get().getId(), 4);

        long idDec = repository.insertActor(actorDec);
        var actorDec1 = repository.selectActorById(idDec);
        assertTrue(actorDec1.isPresent());
        assertEquals(actorDec1.get().getId(), 5);

    }

    @Test
    @Order(4)
    void deleteActor() {
        Long id = 1L;
        int result = repository.deleteActor(id);
        assertEquals(1, result);

        Long id1 = 223L;
        int result1 = repository.deleteActor(id1);
        assertEquals(0, result1);
    }

    @Test
    @Order(5)
    void selectActorById() {
        Long id = 2L;
        Optional<Actor> selected = repository.selectActorById(id);
        assertEquals(selected.orElseThrow().getFullName(), "Alan Arkin");

        Long id1 = 202L;
        Optional<Actor> selected1 = repository.selectActorById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(6)
    void updateActor() {
        Actor act = repository.selectActorById(2L).orElseThrow();
        act.setFullName("Liza Minelli");
        int result = repository.updateActor(2L, act);
        assertEquals(1, result);
        assertNotEquals(act.getFullName(), "Alan Arkin");
        assertEquals(act.getFullName(), "Liza Minelli");
        assertEquals(act.getBirthDate(), LocalDate.of(1934, Month.MARCH, 26));

        Actor act1 = new Actor("Monica Vitti",
            LocalDate.of(1931, Month.NOVEMBER, 3),
            LocalDate.of(2022, Month.FEBRUARY, 2));
        int result1 = repository.updateActor(202L, act1);
        assertEquals(0, result1);
    }
}