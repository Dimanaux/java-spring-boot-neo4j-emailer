package com.example.emailer.forms.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EmailAddressValidator implements ConstraintValidator<EmailAddressConstraint, String> {
    public void initialize(EmailAddressConstraint constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
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
        if (host.length() > 253) {
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
