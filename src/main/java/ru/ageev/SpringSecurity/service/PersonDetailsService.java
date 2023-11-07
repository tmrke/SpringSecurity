package ru.ageev.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.repositories.PeopleRepositories;
import ru.ageev.SpringSecurity.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public PersonDetailsService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepositories.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found :(");
        }

        return new PersonDetails(person.get());
    }
}
