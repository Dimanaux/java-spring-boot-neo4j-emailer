package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.services.functions.MessageDestroyer;
import com.example.emailer.services.functions.MessageReader;
import com.example.emailer.services.functions.MessageSender;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> findAllAvailableTo(Account account);

    MessageSender send(MessageForm m);

    MessageSender saveToDrafts(MessageForm m);

    List<Message> findDraftsOf(Account account);

    Optional<Message> find(long messageId);

    MessageReader can(Account account);

    MessageDestroyer delete(Long messageId);

    List<Message> findSentBy(Account account);
}
