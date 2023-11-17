package ru.ageev.SpringSecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.service.RegistrationService;
import ru.ageev.SpringSecurity.util.PersonErrorResponse;
import ru.ageev.SpringSecurity.util.PersonNotFoundException;
import ru.ageev.SpringSecurity.util.PersonNotValidException;
import ru.ageev.SpringSecurity.util.PersonValidator;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errorList = bindingResult.getFieldErrors();

            for (FieldError error : errorList) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotValidException(errorMsg.toString(), System.currentTimeMillis());
        }

        registrationService.registration(person);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotValidException e) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
