package com.example.emailer.controllers;

import com.example.emailer.forms.SignUpForm;
import com.example.emailer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/sign_up")
public class SignUpController {

    private final AccountService accountService;

    @Autowired
    public SignUpController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getSignUpPage() {
        return "sign_up";
    }

    @PostMapping
    public String signUpUser(SignUpForm signUpForm) {
        accountService.signUp(signUpForm);
        return "redirect:/sign_in";
    }
}
