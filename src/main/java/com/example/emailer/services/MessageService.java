package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.MessageForm;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface MessageService {
    Consumer<Account> send(MessageForm m);

    Consumer<Account> saveToDrafts(MessageForm m);

    List<Message> findDraftsOf(Account account);

    Optional<Message> find(long messageId);

    List<Message> findBelongingToAccount(Account account);

    Predicate<Message> can(Account account);

    Consumer<Account> delete(Long messageId);

    List<Message> findSentBy(Account account);

    Search search(Account account);

    interface Search {
        Search to(String to);

        Search from(String from);

        Search subject(String subject);

        Search content(String content);

        Stream<Message> get();
    }
}
