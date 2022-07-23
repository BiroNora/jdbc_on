package com.norab.actor;

import com.norab.utils.DeleteResult;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")
@SpringBootTest
class ActorRepositoryTest {
    @Autowired
    private ActorRepository repository;


    @Test
    void selectActors() {
        List<Person> actors = repository.selectActors();
        for (Person a : actors) {
            System.out.print(a.getActorId() + " ");
            System.out.println(a.getFullName());
        }
        assertTrue(actors.size() > 0);
    }

    @Test
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
    void deleteActor_InvalidId() {
        assertEquals(DeleteResult.INVALID_ID, repository.deleteActor(2255, false));
        assertEquals(DeleteResult.INVALID_ID, repository.deleteActor(2255, true));
    }

    @Test
    void deleteActor_NoReference() {
        Person actor = new Person("Julius Cesare", (short) 100, (short) 44);
        int actorId = repository.insertActor(actor);
        assertEquals(DeleteResult.SUCCESS, repository.deleteActor(actorId, false));

        actorId = repository.insertActor(actor);
        assertEquals(DeleteResult.SUCCESS, repository.deleteActor(actorId, true));
    }

    @Test
    void deleteActor_WithReferences() {
        assertEquals(DeleteResult.HAS_REFERENCES, repository.deleteActor(6, false));
        assertEquals(DeleteResult.SUCCESS, repository.deleteActor(6, true));
        //TODO: actor 6!
    }

    @Test
    void selectActorById() {
        Person actor = new Person("Julius Cesare", (short) 100, (short) 44);
        int actorId = repository.insertActor(actor);
        Optional<Person> selected = repository.selectActorById(actorId);
        assertTrue(selected.isPresent());
        assertEquals("Julius Cesare", selected.get().getFullName());

        Integer id1 = 2202;
        Optional<Person> selected1 = repository.selectActorById(id1);
        assertTrue(selected1.isEmpty());
    }

    @Test
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
    void updateActorWithInvalidId() {
        Person act1 = new Person("Monica Vitti",
            (short) 1931,
            (short) 2022);
        int result1 = repository.updateActor(202, act1);
        assertEquals(0, result1);
    }
}
