package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.services.functions.Creator;
import com.example.emailer.services.functions.MessageDestroyer;
import com.example.emailer.services.functions.MessageReader;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Creator send(MessageForm m);

    Creator saveToDrafts(MessageForm m);

    List<Message> findDraftsOf(Account account);

    Optional<Message> find(long messageId);

    List<Message> findBelongingToAccount(Account account);

    MessageReader can(Account account);

    MessageDestroyer delete(Long messageId);

    List<Message> findSentBy(Account account);
}
