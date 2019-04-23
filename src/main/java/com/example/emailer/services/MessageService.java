package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;

import java.util.List;

public interface MessageService {
    List<Message> findAllAvailableTo(Account account);

    Message toMessage(MessageForm messageForm);

    void send(Message message);

    void saveToDrafts(Message draft);
}
