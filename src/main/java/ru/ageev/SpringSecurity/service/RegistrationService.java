package ru.ageev.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.repositories.PeopleRepositories;

import java.util.Optional;

@Service
public class RegistrationService {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public RegistrationService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public void registration(Person person) {
        peopleRepositories.save(person);
    }
}
