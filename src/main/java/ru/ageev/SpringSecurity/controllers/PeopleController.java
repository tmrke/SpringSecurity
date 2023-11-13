package ru.ageev.SpringSecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.security.PersonDetails;

@Controller
public class PeopleController {

    @GetMapping("person")
    public String person(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Person person = personDetails.getPerson();
        model.addAttribute(person);

        return "person";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Person person = personDetails.getPerson();
        model.addAttribute(person);

        return "admin";
    }
}
