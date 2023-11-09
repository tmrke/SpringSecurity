package ru.ageev.SpringSecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.service.RegistrationService;
import ru.ageev.SpringSecurity.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("registration")
    public String registration(@ModelAttribute Person person) {
        return "auth/registration";
    }

    @PostMapping("registration")
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        registrationService.registration(person);

        return "redirect:auth/login";
    }
}
