package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        modelMap.put("groups", account.getGroups());
        return "inbox";
    }

    @GetMapping(path = "/compose")
    public String getMessageForm() {
        return "compose_message";
    }


    @GetMapping(path = "/drafts")
    public String getDrafts(ModelMap modelMap,
                            @AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        List<Message> drafts = messageService.findDraftsOf(account);
        modelMap.put("messages", drafts);
        modelMap.put("groups", account.getGroups());

        return "inbox";
    }

    @GetMapping(path = "/sent")
    public String getSent(ModelMap modelMap,
                          @AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        List<Message> sentMessages = messageService.findSentBy(account);
        modelMap.put("messages", sentMessages);
        modelMap.put("groups", account.getGroups());

        return "inbox";
    }
}
