package dev.samuk.auth.valid;

import dev.samuk.auth.entity.User;
import dev.samuk.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, User> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        System.out.println("Calllll");
        System.out.println(value);
        if (value != null) {
            return !repository.existsByEmail(value.getEmail());
        }

        return false;
    }
}
