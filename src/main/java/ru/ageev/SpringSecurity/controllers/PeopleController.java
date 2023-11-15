package ru.ageev.SpringSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.repositories.PeopleRepositories;
import ru.ageev.SpringSecurity.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Controller
public class PeopleController {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public PeopleController(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    @GetMapping("owner")
    public String person(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Person person = personDetails.getPerson();
        model.addAttribute(person);

        return "owner";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Person person = personDetails.getPerson();
        model.addAttribute(person);

        return "admin";
    }

    @GetMapping("people")
    @ResponseBody
    public List<Person> people() {
        return peopleRepositories.findAll();
    }


    @GetMapping("people/{id}")
    @ResponseBody
    public Person person(@PathVariable("id") int id) {
        return peopleRepositories.findById(id).orElse(null);
    }
}
