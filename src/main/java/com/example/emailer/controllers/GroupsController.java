package com.example.emailer.controllers;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Group;
import com.example.emailer.forms.GroupForm;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/groups")
public class GroupsController {
    private final GroupService groupService;

    @Autowired
    public GroupsController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String getGroups(ModelMap modelMap, @AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        modelMap.put("groups", account.getGroups());
        return "groups";
    }

    @PostMapping
    public String createGroup(GroupForm group, @AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        groupService.create(group).by(account);
        return "redirect:/groups";
    }

    @PostMapping(path = "/{id}")
    public String joinGroup(@PathVariable("id") Long groupId,
                            @AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        Optional<Group> group = groupService.findById(groupId);

        group.ifPresent(g -> g.getContacts().add(account));
        return "redirect:/groups";
    }
}
