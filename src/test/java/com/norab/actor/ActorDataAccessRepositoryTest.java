package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ActorDataAccessRepositoryTest {
    @Autowired
    private ActorDataAccessRepository repository;

    @Test
    void selectActorByValidId() {
        Actor actor = new Actor("John Wickels",
            LocalDate.of(2000, Month.DECEMBER, 12),
            LocalDate.of(2053, Month.FEBRUARY, 10));
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

    /*@Test
    void whenSelectFromInMemoryActorById_thenReturnCorrectActor() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setJdbcTemplate(jdbcTemplate);

        assertEquals(4, employeeDAO.getCountOfEmployees());
    }*/

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