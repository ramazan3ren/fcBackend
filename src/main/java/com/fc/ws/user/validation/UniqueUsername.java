package com.fc.ws.user.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "Bu kullanıcı adı maalesef alınmış.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
