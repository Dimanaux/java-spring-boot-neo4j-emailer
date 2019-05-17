package com.example.emailer.forms.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LatinOnlyContstraint.class)
public @interface LatinOnly {
    String message() default "only latin is allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
