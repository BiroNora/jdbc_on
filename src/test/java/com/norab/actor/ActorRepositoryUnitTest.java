package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class ActorRepositoryUnitTest {
    @Autowired
    private ActorRepository repo;

    @Test
    void selectActorByValidId() {
        Integer id = 1;
        Person actor = new Person("John Wick",
            LocalDate.of(2000, Month.DECEMBER, 12),
            LocalDate.of(2053, Month.FEBRUARY, 10));
        repo.insertActor(actor);

        Optional<Person> expected = repo.selectActorById(id);

        assertThat(expected).isPresent();
    }

}
