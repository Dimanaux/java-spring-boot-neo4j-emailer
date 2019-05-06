package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/inbox/messages")
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/{id}")
    public String getMessage(@PathVariable("id") int messageId,
                             ModelMap modelMap,
                             @AuthenticationPrincipal AccountDetails accountDetails) {
        Optional<Message> message = messageService.find(messageId);
        Account account = accountDetails.getAccount();

        if (message.isPresent() && messageService.can(account).read(message.get())) {
            modelMap.put("message", message.get());
            modelMap.put("current_user", account);
            return "message";
        } else {
            throw new AccessDeniedException("Access denied. You don't have permissions to read this message");
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMessage(@PathVariable("id") Long messageId,
                              @AuthenticationPrincipal AccountDetails accountDetails) {
        messageService.delete(messageId).from(accountDetails.getAccount());
    }

    @PostMapping
    public String sendMessage(MessageForm messageForm,
                              @AuthenticationPrincipal AccountDetails accountDetails) {
        Account sender = accountDetails.getAccount();

        messageService.send(messageForm).by(sender);

        return "redirect:/inbox";
    }

    @PostMapping(path = "/drafts")
    public String saveToDrafts(MessageForm messageForm,
                               @AuthenticationPrincipal AccountDetails accountDetails) {
        Account sender = accountDetails.getAccount();

        messageService.saveToDrafts(messageForm).by(sender);

        return "redirect:/inbox";

    }
}