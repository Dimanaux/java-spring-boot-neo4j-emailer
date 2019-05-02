package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/inbox")
public class InboxController {
    private final MessageService messageService;
    private final AccountRepository accountRepository;

    @Autowired
    public InboxController(MessageService messageService, AccountRepository accountRepository) {
        this.messageService = messageService;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public String getInbox(@AuthenticationPrincipal AccountDetails accountDetails,
                           ModelMap modelMap) {
        Account account = accountDetails.getAccount();
        List<Message> messages = messageService.findAllAvailableTo(account);
        modelMap.put("messages", messages);
        return "inbox";
    }

    @GetMapping(path = "/send")
    public String getMessageForm() {
        return "compose_message";
    }

    @PostMapping(path = "/send")
    public String sendMessage(MessageForm messageForm,
                              @AuthenticationPrincipal AccountDetails accountDetails) {
        Account sender = accountDetails.getAccount();

        messageService.send(messageForm).by(sender);

        return "redirect:/inbox";
    }

    @PostMapping(path = "/draft")
    public String saveDraft(MessageForm messageForm,
                            @AuthenticationPrincipal AccountDetails accountDetails) {
        Account sender = accountDetails.getAccount();

        messageService.saveToDrafts(messageForm).by(sender);

        return "redirect:/inbox";
    }
}
