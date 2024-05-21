package com.fc.ws.facility.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.fc.ws.facility.Facility;
import com.fc.ws.facility.FacilityRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidatorFacility implements ConstraintValidator<UniqeEmailFacility, String> {

    @Autowired
    FacilityRepository facilityRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Facility inDB = facilityRepository.findByEmail(value);

        return inDB == null;

    }

}
