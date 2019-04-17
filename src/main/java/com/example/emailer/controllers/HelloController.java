package com.example.emailer.controllers;

import com.example.emailer.db.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/welcome")
public class HelloController {
    private final AccountRepository accountRepository;

    @Autowired
    public HelloController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public String hello(ModelMap modelMap) {
        modelMap.put("accounts", accountRepository.findAll());
        return "users";
    }
}
