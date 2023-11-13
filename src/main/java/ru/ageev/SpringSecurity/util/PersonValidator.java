package ru.ageev.SpringSecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ageev.SpringSecurity.models.Person;
import ru.ageev.SpringSecurity.service.RegistrationValidService;

@Component
public class PersonValidator implements Validator {
    private final RegistrationValidService registrationValidService;

    @Autowired
    public PersonValidator(RegistrationValidService registrationValidService) {
        this.registrationValidService = registrationValidService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        boolean personIsPresent = registrationValidService.getOptional(person.getUsername()).isPresent();

        if (personIsPresent) {
            errors.rejectValue("username", "001", "Пользователь с таким именем уже существует");
        }

        if (errors.hasFieldErrors("date")) {
            errors.rejectValue("date", "002", "Ошибка формата даты");
        }
    }
}
