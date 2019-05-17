package com.example.emailer.controllers;

import com.example.emailer.db.entities.Folder;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.FolderForm;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.FolderService;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/inbox/folders")
public class FoldersController {
    private final FolderService folderService;
    private final MessageService messageService;

    @Autowired
    public FoldersController(FolderService folderService, MessageService messageService) {
        this.folderService = folderService;
        this.messageService = messageService;
    }

    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FolderForm> create(@AuthenticationPrincipal AccountDetails accountDetails,
                                             FolderForm folderForm) {
        folderService.create(folderForm).apply(accountDetails.getAccount());
        return ResponseEntity.ok(folderForm);
    }

    @GetMapping(path = "/{folderName}")
    public String listFolder(@AuthenticationPrincipal AccountDetails accountDetails,
                             ModelMap modelMap,
                             @PathVariable String folderName) {
//        Optional<Folder> folder = folderService.find(folderName, accountDetails.getAccount());
        Optional<Folder> folder = accountDetails.getAccount().getFolders()
                .stream()
                .filter(f -> folderName.equals(f.getName()))
                .findAny();
        Optional<List<Message>> messages = folder.map(folderService::getMessages);
        modelMap.put("messages", messages.orElse(Collections.emptyList()));
        return "inbox";
    }

    @ResponseBody
    @PostMapping(path = "/{folderName}")
    public ResponseEntity addToFolder(@AuthenticationPrincipal AccountDetails accountDetails,
                                      @RequestParam("messageId") long messageId,
                                      @PathVariable String folderName) {
        Optional<Folder> folder = folderService.find(folderName, accountDetails.getAccount());
        Optional<Message> message = messageService.find(messageId);
        if (folder.isPresent() && message.isPresent()) {
            folderService.put(folder.get()).accept(message.get());
            return ResponseEntity.ok().build();
        } else {
            throw new IllegalArgumentException("No such folder or message");
        }
    }
}
