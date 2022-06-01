package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActorDataAccessRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private ActorDataAccessRepository dao;

    @Autowired
    public ActorDataAccessRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        dao = new ActorDataAccessRepository(jdbcTemplate);
    }

    @Test
    void selectActors() {
        List<Actor> actors = dao.selectActors();
        assertEquals(7, actors.size());
    }

    @Test
    void selectActorByValidId() {
        Optional<Actor> actor = dao.selectActorById(6);
        assertTrue(actor.isPresent());
    }

    @Test
    void selectActorByInvalidId() {
        Optional<Actor> actor = dao.selectActorById(99);
        assertFalse(actor.isPresent());
    }

    @Test
    void insertActor() {
        Actor actor = new Actor(14, "Test_nameEE", LocalDate.parse("2222-05-01"));
        dao.insertActor(actor);
        
        List<Actor> actors = dao.selectActors();
        assertEquals(10, actors.size());
        assertEquals("Test_nameEE", actors.get(9).fullName());
        assertEquals(LocalDate.parse("2222-05-01"), actors.get(9).birthDate());
    }

    @Test
    void updateActor() {
        Actor actor = dao.selectActorById(9).get();
        Actor actor1 = new Actor(9, "Test_namesD", LocalDate.parse("2222-05-01"));
        dao.updateActor(9, actor1);

        Actor updated = dao.selectActorById(9).get();
        assertEquals("Test_namesD", updated.fullName());
    }

    @Test
    void deleteActor() {
        dao.deleteActor(14);

        List<Actor> actors = dao.selectActors();
        assertEquals(6, actors.size());
    }

}
