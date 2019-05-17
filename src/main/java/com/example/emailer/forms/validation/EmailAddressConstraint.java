package com.example.emailer.forms.validation;

import com.example.emailer.forms.SignUpForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class EmailAddressConstraint implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return SignUpForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
        SignUpForm signUpForm = (SignUpForm) o;
        if (isValidEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email");
        }
    }

    private boolean isValidEmail(String email) {
        String[] split = email.split("@");
        if (split.length != 2) {
            return false;
        }

        String username = split[0];
        String host = split[1];
        return isValidUsername(username) && isValidHost(host);
    }

    private boolean isValidUsername(String username) {
        return username.codePoints().mapToObj(c -> (char) c)
                .map(this::isAllowed)
                .reduce(Boolean::logicalAnd)
                .orElse(false);
    }

    private boolean isAllowed(char c) {
        //  latin letters, digits, . and -
        return Character.isLetter(c) || Character.isDigit(c) || c == '-' || c == '.';
    }

    private boolean isValidHost(String host) {
        if (host.length() > 253 || host.isEmpty()) {
            return false;
        }

        if (Arrays.stream(host.split("\\.")).anyMatch(s -> s.length() > 63 || s.isEmpty())) {
            return false;
        }

        return host.codePoints().mapToObj(c -> (char) c).allMatch(this::rfc1123Symbol);
    }

    private boolean rfc1123Symbol(char c) {
        return Character.isLowerCase(c) || Character.isDigit(c) || c == '-';
    }
}
