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
        Long id = 1L;
        Actor actor = new Actor("John Wick",
            LocalDate.of(2000, Month.DECEMBER, 12),
            LocalDate.of(2053, Month.FEBRUARY, 10));
        repo.insertActor(actor);

        Optional<Actor> expected = repo.selectActorById(id);

        assertThat(expected).isPresent();
    }

}
