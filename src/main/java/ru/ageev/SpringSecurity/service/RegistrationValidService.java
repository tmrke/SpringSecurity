package ru.ageev.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.repositories.PeopleRepositories;

import java.util.Optional;

@Service
public class RegistrationValidService {

    private final PeopleRepositories peopleRepositories;

    @Autowired
    public RegistrationValidService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public Optional<Person> getOptional(String username) {
        return peopleRepositories.findByUsername(username);
    }
}
