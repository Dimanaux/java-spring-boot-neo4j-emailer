package com.example.emailer.controllers;

import com.example.emailer.forms.MessageForm;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RootController {
    private final AccountService accountService;

    @Autowired
    public RootController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String getRoot() {
        return "redirect:/inbox";
    }


    @GetMapping(path = "/signature")
    public String signature(@AuthenticationPrincipal AccountDetails accountDetails,
                            ModelMap modelMap) {
        return "signature";
    }

    @PostMapping(path = "/signature")
    public String setSignature(@AuthenticationPrincipal AccountDetails accountDetails,
                               MessageForm messageForm) {
        accountService.signature(messageForm.getContent()).accept(accountDetails.getAccount());
        return "redirect:/signature";
    }
}
