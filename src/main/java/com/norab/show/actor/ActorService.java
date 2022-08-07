package com.norab.show.actor;

import com.norab.exception.AlreadyExistsException;
import com.norab.exception.InternalServerExeption;
import com.norab.exception.NotFoundException;
import com.norab.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Person> listActors(Page page) {
        List<Person> actors = actorDao.listActors(page);
        return actors;
    }

    public int insertActor(Person actor) {
        List<Person> people = selectActorByName(actor.getFullName(), true);
        String actorName = actor.getFullName();
        List<Person> collect = people.stream()
            .filter(x -> x.getFullName().equals(actorName)).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("This artist already exists");
        }
        return actorDao.insertActor(actor);
    }

    public void deleteActor(Integer actorId, boolean force) {
        switch (actorDao.deleteActor(actorId, force)) {
            case INVALID_ID:
                throw new NotFoundException(String.format("Actor with id %s not found", actorId));
            case JDBC_ERROR:
                throw new InternalServerExeption("Could not delete actor");
            case HAS_REFERENCES:
                throw new AlreadyExistsException("Warning: this actor has references");
            case SUCCESS:
        }
    }

    public Person getActor(Integer actorId) {
        try {
            return (Person) actorDao.selectActorById(actorId).orElseThrow(
                () -> new NotFoundException(String.format("Actor with id %s not found", actorId)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateActor(Integer actorId, Person actor) {
        if (actorDao.selectActorById(actorId).isPresent()) {
            Person actor1 = new Person(actorId, actor.getFullName(), actor.getBirthDate(), actor.getDeathDate());
            actorDao.updateActor(actorId, actor1);
        } else {
            throw new NotFoundException(String.format("Actor with id %s not found", actorId));
        }
    }

    public List<Person> selectActorByName(String name, boolean match) {
        return actorDao.selectActorByName(name, match);
    }
}
