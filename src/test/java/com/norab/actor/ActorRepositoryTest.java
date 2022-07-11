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
        assertEquals(actors.size(), 3);
        assertEquals(actors.get(2).getFullName(), "Geoffry Rush");
    }

    @Test
    @Order(3)
    void insertLiving_DeceasedActor() {
        Person actorLiv = new Person("Helen Hunt",
            LocalDate.of(1963, Month.JUNE, 15));
        Person actorDec = new Person("Marlon Brando",
            LocalDate.of(1924, Month.APRIL, 3),
            LocalDate.of(2004, Month.JULY, 1));

        int idLiv = repository.insertActor(actorLiv);
        var actorLiv1 = repository.selectActorById(idLiv);
        assertTrue(actorLiv1.isPresent());
        assertEquals(actorLiv1.get().getActorId(), 4);

        int idDec = repository.insertActor(actorDec);
        var actorDec1 = repository.selectActorById(idDec);
        assertTrue(actorDec1.isPresent());
        assertEquals(actorDec1.get().getActorId(), 5);

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
        assertEquals(selected.orElseThrow().getFullName(), "Alan Arkin");

        Integer id1 = 202;
        Optional<Person> selected1 = repository.selectActorById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
    @Order(6)
    void updateActor() {
        Person act = repository.selectActorById(2).orElseThrow();
        act.setFullName("Liza Minelli");
        int result = repository.updateActor(2, act);
        assertEquals(1, result);
        assertNotEquals(act.getFullName(), "Alan Arkin");
        assertEquals(act.getFullName(), "Liza Minelli");
        assertEquals(act.getBirthDate(), LocalDate.of(1934, Month.MARCH, 26));

        Person act1 = new Person("Monica Vitti",
            LocalDate.of(1931, Month.NOVEMBER, 3),
            LocalDate.of(2022, Month.FEBRUARY, 2));
        int result1 = repository.updateActor(202, act1);
        assertEquals(0, result1);
    }
}