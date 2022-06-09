package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

import static org.testng.AssertJUnit.assertNotNull;

@Sql({"/schema.sql", "/data.sql"})
@SpringBootTest
class ActorDataAccessRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Test
    void ifNotNull() {
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
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