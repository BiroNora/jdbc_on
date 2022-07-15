package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@ActiveProfiles("test")
@SpringBootTest
public class ActorUnitTest {
    @Autowired
    private ActorRepository repo;

    @Test
    void insertActor() {
        Person actor = new Person("John Wick",
            (short) 2000,
            (short) 2053);
        Integer id = repo.insertActor(actor);

        Optional<Person> expected = repo.selectActorById(id);

        assertThat(expected).isPresent();
        assertThat(expected.get()).isEqualTo(actor);
    }

}
