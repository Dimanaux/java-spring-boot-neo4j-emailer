package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.GroupService;
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
    private final MessageService messages;
    private final GroupService groupService;

    @Autowired
    public InboxController(MessageService messageService,
                           GroupService groupService) {
        this.messages = messageService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getInbox(@AuthenticationPrincipal AccountDetails accountDetails,
                           ModelMap modelMap) {
        Account account = accountDetails.getAccount();
        List<Message> all = messages.findBelongingToAccount(account);
        modelMap.put("messages", all);
        return "inbox";
    }

    @GetMapping(path = "/compose")
    public String getMessageForm(@AuthenticationPrincipal AccountDetails accountDetails,
                                 ModelMap modelMap) {
        Account account = accountDetails.getAccount();
        modelMap.put("contacts", groupService.contactsOf(account));
        return "compose_message";
    }


    @GetMapping(path = "/drafts")
    public String getDrafts(@AuthenticationPrincipal AccountDetails accountDetails,
                            ModelMap modelMap) {
        Account account = accountDetails.getAccount();
        List<Message> drafts = messages.findDraftsOf(account);
        modelMap.put("messages", drafts);

        return "inbox";
    }

    @GetMapping(path = "/sent")
    public String getSent(@AuthenticationPrincipal AccountDetails accountDetails,
                          ModelMap modelMap) {
        Account account = accountDetails.getAccount();
        List<Message> sentMessages = messages.findSentBy(account);
        modelMap.put("messages", sentMessages);

        return "inbox";
    }
}
