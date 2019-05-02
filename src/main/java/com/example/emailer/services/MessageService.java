package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;

import java.util.List;

public interface MessageService {
    List<Message> findAllAvailableTo(Account account);

    MessageSender send(MessageForm messageForm);

    MessageSender saveToDrafts(MessageForm messageForm);
}
