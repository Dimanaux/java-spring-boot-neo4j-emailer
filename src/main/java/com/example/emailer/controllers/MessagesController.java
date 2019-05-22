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
    public String getMessage(@AuthenticationPrincipal AccountDetails accountDetails,
                             ModelMap modelMap,
                             @PathVariable("id") int messageId) {
        Optional<Message> message = messageService.find(messageId);
        Account account = accountDetails.getAccount();

        if (message.isPresent() && messageService.can(account).test(message.get())) {
            modelMap.put("message", message.get());
            modelMap.put("current_user", account);
            modelMap.put("folders", account.getFolders());
            return "message";
        } else {
            throw new AccessDeniedException("Access denied. You don't have permissions to read this message");
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMessage(@PathVariable("id") Long messageId,
                              @AuthenticationPrincipal AccountDetails accountDetails) {
        messageService.delete(messageId).accept(accountDetails.getAccount());
    }

    @PostMapping
    public String sendMessage(@AuthenticationPrincipal AccountDetails accountDetails,
                              MessageForm messageForm) {
        Account sender = accountDetails.getAccount();

        messageService.send(messageForm).accept(sender);

        return "redirect:/inbox";
    }

    @PostMapping(path = "/drafts")
    public String saveToDrafts(@AuthenticationPrincipal AccountDetails accountDetails,
                               MessageForm messageForm) {
        Account sender = accountDetails.getAccount();

        messageService.saveToDrafts(messageForm).accept(sender);

        return "redirect:/inbox";
    }
}
