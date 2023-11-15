package ru.ageev.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.repositories.PeopleRepositories;
import ru.ageev.SpringSecurity.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public PeopleService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public List<Person> findAll() {
        return peopleRepositories.findAll();
    }

    public Person findById(int id) {
        Optional<Person> optionalPerson = peopleRepositories.findById(id);

        return optionalPerson.orElseThrow(PersonNotFoundException::new);
    }
}
