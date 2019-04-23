package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
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

@Controller
@RequestMapping(path = "/inbox")
public class InboxController {
    private final MessageService messageService;

    @Autowired
    public InboxController(MessageService messageService) {
        this.messageService = messageService;
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
        Message message = messageService.toMessage(messageForm);
        message.setSender(accountDetails.getAccount());

        messageService.send(message);

        return "redirect:/inbox";
    }

    @PostMapping(path = "/draft")
    public String saveDraft(MessageForm messageForm,
                            @AuthenticationPrincipal AccountDetails accountDetails) {
        Message draft = messageService.toMessage(messageForm);
        draft.setSender(accountDetails.getAccount());

        messageService.saveToDrafts(draft);

        return "redirect:/inbox";
    }
}
