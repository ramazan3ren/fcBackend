package com.fc.ws.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.fc.ws.user.User;
import com.fc.ws.user.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        User inDB = userRepository.findByEmail(value);

        return inDB == null;

    }

}
