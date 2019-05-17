package com.example.emailer.forms.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatinOnlyContstraint implements ConstraintValidator<LatinOnly, String> {
    public void initialize(LatinOnly constraint) {
    }

    public boolean isValid(String string, ConstraintValidatorContext context) {
        return string.codePoints()
                .mapToObj(c -> (char) c)
                .allMatch(c -> ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == ' ');
    }
}
