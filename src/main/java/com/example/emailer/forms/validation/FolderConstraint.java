package com.example.emailer.forms.validation;

import com.example.emailer.forms.FolderForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FolderConstraint implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FolderForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        FolderForm folderForm = (FolderForm) o;
        if (!isLatin(folderForm.getName())) {
            errors.rejectValue("name", "non.latin.symbols");
        }
    }

    private boolean isLatin(String string) {
        return string.codePoints()
                .mapToObj(c -> (char) c)
                .allMatch(c -> ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == ' ');
    }
}
