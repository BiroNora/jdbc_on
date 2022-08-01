package com.norab.role;

import com.norab.actor.ActorRepository;
import com.norab.actor.Person;
import com.norab.exception.InvalidInputException;
import com.norab.movie.Movie;
import com.norab.movie.MovieRepository;
import com.norab.utils.DeleteResult;
import com.norab.utils.Page;
import com.norab.utils.ResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void listRoles() {
        Page page = new Page(1, 10);
        List<Plays> roles = repository.listRoles(page);
        for (Plays p : roles) {
            System.out.print(p.getRoleId() + ". ");
            System.out.println(p.getRoleName());
        }
        assertEquals(10, roles.size());
    }

    @Test
    void selectRolesByName() {
        String rolename = "el";
        List<ResultResponse> result = repository.selectRolesByName(rolename);
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void insertRole() {
        Plays plays = new Plays("Sheryl Hoover", 2, 3);

        int roleId = repository.insertRole(plays);
        Optional<Plays> role1 = repository.selectRoleById(roleId);
        assertTrue(role1.isPresent());
        assertEquals(role1.get().getRoleName(), "Sheryl Hoover");
        assertEquals(role1.get().getMovieId(), 2);
        assertEquals(role1.get().getActorId(), 3);
    }

    @Test
    void deleteRole() {
        Plays plays = new Plays("Olive Hoover", 2, 3);
        int roleId = repository.insertRole(plays);
        int result = repository.deleteRole(roleId);
        assertEquals(1, result);

        Integer roleId1 = 772;
        int result1 = repository.deleteRole(roleId1);
        assertEquals(0, result1);
    }

    @Test
    void selectRoleByRole_InvalidInsert() {
        Plays plays = new Plays("Jack Sparrow", 1, 1);
        boolean b = repository.selectRoleByRole(plays);
        assertTrue(b);
    }

    @Test
    void selectRoleByRole_ValidInsert() {
        Plays plays = new Plays("Jack Sparrow", 1, 1);
        boolean b = repository.selectRoleByRole(plays);
        assertTrue(b);
    }

    @Test
    void selectRoleByValidId() {
        Page page = new Page(1, 10);
        Plays plays = new Plays("Olive Hoover", 2, 2);
        try {
            int roleId = repository.insertRole(plays);
            var plays1 = repository.selectRoleById(roleId);
            assertTrue(plays1.isPresent());
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        List<Plays> expected = repository.listRoles(page);

        assertNotNull(expected);
        for (Plays p : expected) {
            System.out.println(p.getRoleId());
            System.out.println(p.getRoleName());
        }
        assertTrue(expected.size() != 0);
    }

    @Test
    void selectRoleByValidId1() {
        Integer roleId = 3;
        Optional<Plays> selected = repository.selectRoleById(roleId);
        assertTrue(selected.isPresent());
    }

    @Test
    void selectRoleByInvalidId() {
        var plays = repository.selectRoleById(1024);
        assertTrue(plays.isEmpty());
    }

    @Test
    void updateRole() {
        Plays role = repository.selectRoleById(3).orElseThrow();
        System.out.println(role);
        role.setRoleName("Jimi Hendrix");
        role.setMovieId(2);
        role.setActorId(3);
        System.out.println(role);
        int result = repository.updateRole(3, role);
        assertEquals(1, result);
        assertNotEquals(role.getRoleName(), "Jimmy McGinty");
    }

    @Test
    void updateRoleByInvalidIds() {
        Plays role = repository.selectRoleById(3).orElseThrow();
        System.out.println(role);
        role.setMovieId(202);
        role.setActorId(3);
        assertEquals(role.getActorId(), 3);

        System.out.println(role);
        assertThrows(InvalidInputException.class, () -> {
            repository.updateRole(3, role);
        });

        Plays role1 = repository.selectRoleById(3).orElseThrow();
        System.out.println(role1);
        role1.setMovieId(1);
        role1.setActorId(333);
        System.out.println(role1);
        assertThrows(InvalidInputException.class, () -> {
            repository.updateRole(3, role1);
        });

        Plays role3 = new Plays("Paul Vitti", 2, 2);
        int result = repository.updateRole(255587, role3);
        assertEquals(0, result);
    }

    @Test
    void deleteReferredMovie() {
        Movie movie = new Movie("Kleo", "Patra", (short) 2002);
        int movieId = movieRepository.insertMovie(movie);

        Plays plays = new Plays("Julius Cezar", movieId, 2);
        int roleId = repository.insertRole(plays);

        assertEquals(DeleteResult.HAS_REFERENCES, movieRepository.deleteMovie(movieId, false));

        Optional<Plays> plays1 = repository.selectRoleById(roleId);
        assertTrue(plays1.isPresent());
        assertEquals(movieId, plays1.get().getMovieId());
    }

    @Test
    void deleteReferredActor() {
        Person actor = new Person("Greta Garbo", (short) 2002);
        int actorId = actorRepository.insertActor(actor);

        Plays plays = new Plays("Krisztina Királynő", 1, actorId);
        int roleId = repository.insertRole(plays);

        assertEquals(DeleteResult.HAS_REFERENCES, actorRepository.deleteActor(actorId, false));

        Optional<Plays> plays1 = repository.selectRoleById(roleId);
        assertTrue(plays1.isPresent());
        assertEquals(actorId, plays1.get().getActorId());
    }
}
