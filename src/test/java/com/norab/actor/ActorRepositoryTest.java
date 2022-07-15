package com.norab.actor;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        List<Person> actors = repository.selectActors();
        for (Person a : actors) {
            System.out.print(a.getActorId() + " ");
            System.out.println(a.getFullName());
        }
        assertEquals(10, actors.size());
        assertEquals("Geoffrey Rush", actors.get(2).getFullName());
    }

    @Test
    @Order(3)
    void insertAlive_DeceasedActor() {
        Person actorLiv = new Person("Helen Hunt",
            (short) 1963);
        Person actorDec = new Person("Marlon Brando",
            (short) 1924,
            (short) 2004);

        int idLiv = repository.insertActor(actorLiv);
        var actorLiv1 = repository.selectActorById(idLiv);
        assertTrue(actorLiv1.isPresent());
        assertEquals(idLiv, actorLiv1.get().getActorId());

        int idDec = repository.insertActor(actorDec);
        var actorDec1 = repository.selectActorById(idDec);
        assertTrue(actorDec1.isPresent());
        assertEquals(idDec, actorDec1.get().getActorId());

    }

    @Test
    @Order(4)
    void deleteActor() {
        Integer id = 1;
        int result = repository.deleteActor(id);
        assertEquals(1, result);

        Integer id1 = 223;
        int result1 = repository.deleteActor(id1);
        assertEquals(0, result1);
    }

    @Test
    @Order(5)
    void selectActorById() {
        Integer id = 2;
        Optional<Person> selected = repository.selectActorById(id);
        assertEquals("Greg Kinnear", selected.orElseThrow().getFullName());

        Integer id1 = 202;
        Optional<Person> selected1 = repository.selectActorById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(6)
    void updateActor() {
        Integer id = 5;
        Person act0 = repository.selectActorById(id).orElseThrow();
        String origName = act0.getFullName();
        act0.setFullName("Liza Minelli");
        int result = repository.updateActor(id, act0);
        assertEquals(1, result);

        Optional<Person> act1 = repository.selectActorById(id);
        assertTrue(act1.isPresent());
        assertNotEquals(origName, act1.get().getFullName());
        assertEquals("Liza Minelli", act1.get().getFullName());
        assertEquals(act0.getBirthDate(), act1.get().getBirthDate());
    }

    @Test
    @Order(7)
    void updateActorWithInvalidId() {
        Person act1 = new Person("Monica Vitti",
            (short) 1931,
            (short) 2022);
        int result1 = repository.updateActor(202, act1);
        assertEquals(0, result1);
    }
}