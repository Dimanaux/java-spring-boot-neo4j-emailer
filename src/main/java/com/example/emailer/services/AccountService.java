package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.forms.SignUpForm;

import java.util.function.Consumer;

public interface AccountService {
    void signUp(SignUpForm form);

    Consumer<Account> signature(String signature);
}
