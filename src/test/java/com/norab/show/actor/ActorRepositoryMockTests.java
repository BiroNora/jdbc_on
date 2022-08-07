package com.norab.show.actor;

import com.norab.utils.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

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
        Page page = new Page(1, 10);
        when(repository.listActors(page)).thenReturn(Stream
            .of(new Person("Greg Kinnear",
                    (short) 1963,
                    (short) 2070),
                new Person("Max Kinnear",
                    (short) 1963,
                    (short) 2700)
            ).collect(Collectors.toList()));

        System.out.println("Data from DB: " + repository.listActors(page).toString());
        verify(repository).listActors(page);
        assertEquals(2, service.listActors(new Page(1, 10)).size());
    }

    @Test
    void selectActorByValidId() {
        Integer id = 6;
        when(repository.selectActorById(id)).thenReturn(
            Optional.of(new Person(6, "Greg Kinnear",
                (short) 1963,
                (short) 2070)));

        assertNotNull(repository.selectActorById(id));
        verify(repository).selectActorById(id);
        assertEquals("Greg Kinnear", service.getActor(id).getFullName());
    }

    @Test
    void selectActorByInvalidId() {
        Integer id = 6;
        when(repository.selectActorById(id)).thenReturn(null);

        assertNull(repository.selectActorById(id));
        verify(repository).selectActorById(id);
        assertThrows(RuntimeException.class, () -> {
            service.getActor(id);
        });
    }

    @Test
    void insertActor() {
        Person actor = new Person("Greg Kinnear",
            (short) 1963,
            (short) 2070);
        when(repository.insertActor(actor)).thenReturn(1);

        assertEquals(1, repository.insertActor(actor));
        verify(repository).insertActor(actor);
        assertEquals(1, service.insertActor(actor));
    }

    @Test
    void deleteActor() {
        Integer id = 5;
        repository.deleteActor(id, false);
        verify(repository, times(1)).deleteActor(id, false);
    }

    @Test
    void updateActor() {
        Integer id = 2;
        Person actor = new Person(2, "Greg Kinnear",
            (short) 1963,
            (short) 2070);
        when(repository.updateActor(id, actor)).thenReturn(1);

        assertEquals(1, repository.updateActor(id, actor));
        verify(repository).updateActor(id, actor);
    }

}
