package com.fc.ws.facility.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidatorFacility.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqeEmailFacility {

    String message() default "Bu e-posta adresi kullanımda.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
