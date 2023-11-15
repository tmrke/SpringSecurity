package ru.ageev.SpringSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.security.PersonDetails;
import ru.ageev.SpringSecurity.service.PeopleService;
import ru.ageev.SpringSecurity.util.PersonErrorResponse;
import ru.ageev.SpringSecurity.util.PersonNotFoundException;

import java.util.List;

@Controller
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
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
        return peopleService.findAll();
    }


    @GetMapping("people/{id}")
    @ResponseBody
    public Person person(@PathVariable("id") int id) {
        return peopleService.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(
                "Person with this id not found!", System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
