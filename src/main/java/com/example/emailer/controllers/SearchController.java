package com.example.emailer.controllers;

import com.example.emailer.db.entities.Message;
import com.example.emailer.security.AccountDetails;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
public class SearchController {
    private final MessageService messageService;

    @Autowired
    public SearchController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/inbox/search")
    public String getPage(@AuthenticationPrincipal AccountDetails accountDetails,
                          ModelMap modelMap) {
        return "search";
    }

    @ResponseBody
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> searchMessages(@AuthenticationPrincipal AccountDetails accountDetails,
                                    @RequestParam Map<String, String> params) {
        List<Map> messages = messageService.search(accountDetails.getAccount())
                .to(params.getOrDefault("to", ""))
                .from(params.getOrDefault("from", ""))
                .subject(params.getOrDefault("subject", ""))
                .content(params.getOrDefault("content", ""))
                .get()
                .map(this::jsonify)
                .collect(Collectors.toList());
        return messages;
    }

    private Map jsonify(Message message) {
        return new TreeMap<String, Object>() {{
            put("messageId", message.getMessageId());
            put("subject", message.getSubject());
            put("content", message.getContent());
            put("sender", message.getSender().getEmail());
            put("recipients", message.getRecipientsSummary());
            put("sentAt", message.getSentAt().toString());
            put("status", message.getStatus());
        }};
    }
}
