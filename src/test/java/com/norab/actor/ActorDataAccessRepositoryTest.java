package com.norab.actor;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.testng.AssertJUnit.*;

@Sql({"/schema.sql", "/data.sql"})
@ActiveProfiles("test")
class ActorDataAccessRepositoryTest {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

   // private DataSource dataSource;
   @ConfigurationProperties("app.datasource.main")
   public HikariDataSource hikariDataSource() {
       return DataSourceBuilder
           .create()
           .type(HikariDataSource.class)
           .build();
   }
    public ActorDataAccessRepositoryTest() {
        this.jdbcTemplate = new JdbcTemplate(hikariDataSource());
    }

    @Test
    void ifNotNull() {
        //assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
    }

    @Test
    void selectActorByValidId() {
        ActorDataAccessRepository repository = new ActorDataAccessRepository(jdbcTemplate);
        Actor actor = new Actor("John Wick",
            LocalDate.of(2000, Month.DECEMBER, 12),
            LocalDate.of(2053, Month.FEBRUARY, 10));
        int result = repository.insertActor(actor);
        assertEquals(1, result);

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