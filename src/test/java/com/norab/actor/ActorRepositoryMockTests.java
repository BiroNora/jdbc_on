package com.norab.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class ActorRepositoryMockTests {
    @Autowired
    ActorService service;
    @MockBean
    ActorRepository repository;

    @Test
    void selectActors() {
        when(repository.selectActors()).thenReturn(Stream
            .of(new Actor("Greg Kinnear",
                    LocalDate.of(1963, Month.AUGUST, 17),
                    LocalDate.of(2070, Month.AUGUST, 17)),
                new Actor("Max Kinnear",
                    LocalDate.of(1963, Month.AUGUST, 17),
                    LocalDate.of(2700, Month.AUGUST, 1))
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.selectActors().toString());
        verify(repository).selectActors();
        assertEquals(2, service.getActors().size());
    }

    @Test
    void selectActorByValidId() {
        Long id = 6L;
        when(repository.selectActorById(id)).thenReturn(
            Optional.of(new Actor(6L, "Greg Kinnear",
                LocalDate.of(1963, Month.AUGUST, 17),
                LocalDate.of(2070, Month.AUGUST, 17))));

        assertNotNull(repository.selectActorById(id));
        verify(repository).selectActorById(id);
        assertEquals("Greg Kinnear", service.getActor(id).getFullName());
    }

    @Test
    void selectActorByInvalidId() {
        Long id = 6L;
        when(repository.selectActorById(id)).thenReturn(null);

        assertNull(repository.selectActorById(id));
        verify(repository).selectActorById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getActor(id);
        });
    }

    @Test
    void insertActor() {
        Actor actor = new Actor("Greg Kinnear",
            LocalDate.of(1963, Month.AUGUST, 17),
            LocalDate.of(2070, Month.AUGUST, 17));
        when(repository.insertActor(actor)).thenReturn(1L);

        assertEquals(1, repository.insertActor(actor));
        verify(repository).insertActor(actor);
        assertEquals(1, service.addNewActor(actor));
    }

    @Test
    void deleteActor() {
        Long id = 5L;
        repository.deleteActor(id);
        verify(repository, times(1)).deleteActor(id);
    }

    @Test
    void updateActor() {
        Long id = 2L;
        Actor actor = new Actor(2L, "Greg Kinnear",
            LocalDate.of(1963, Month.AUGUST, 17),
            LocalDate.of(2070, Month.AUGUST, 17));
        when(repository.updateActor(id, actor)).thenReturn(1);

        assertEquals(1, repository.updateActor(id, actor));
        verify(repository).updateActor(id, actor);
    }

}
