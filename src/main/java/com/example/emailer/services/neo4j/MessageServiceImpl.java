package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.db.repositories.MessageRepository;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Consumer<Account> send(final MessageForm messageForm) {
        final Message email = toMessage(messageForm);

        return account -> {
            email.setStatus("SENT");
            messageRepository.save(email);

            messageRepository.setSender(account.getAccountId(), email.getMessageId());

            messageRepository.addMessageToAccount(account.getAccountId(), email.getMessageId());

            Consumer<Account> addToEmail = a -> messageRepository.addMessageToAccount(a.getAccountId(), email.getMessageId());
            Consumer<Account> setRecipient = a -> messageRepository.setRecipient(a.getAccountId(), email.getMessageId());
            Consumer<Account> setCopyRecipient = a -> messageRepository.setCopyRecipient(a.getAccountId(), email.getMessageId());
            Consumer<Account> setSecretCopyRecipient = a -> messageRepository.setSecretCopyRecipient(a.getAccountId(), email.getMessageId());

            accountStream(messageForm.getRecipients()).peek(addToEmail).forEach(setRecipient);

            accountStream(messageForm.getCopyRecipients()).peek(addToEmail).forEach(setCopyRecipient);

            accountStream(messageForm.getSecretCopyRecipients()).peek(addToEmail).forEach(setSecretCopyRecipient);
        };
    }

    @Override
    public Consumer<Account> saveToDrafts(final MessageForm messageForm) {
        final Message email = toMessage(messageForm);

        return account -> {
            email.setStatus("DRAFT");
            messageRepository.save(email);

            messageRepository.setSender(account.getAccountId(), email.getMessageId());

            messageRepository.addMessageToAccount(account.getAccountId(), email.getMessageId());

            Consumer<Account> addToEmail = a -> messageRepository.addMessageToAccount(a.getAccountId(), email.getMessageId());
            Consumer<Account> setRecipient = a -> messageRepository.setRecipient(a.getAccountId(), email.getMessageId());
            Consumer<Account> setCopyRecipient = a -> messageRepository.setCopyRecipient(a.getAccountId(), email.getMessageId());
            Consumer<Account> setSecretCopyRecipient = a -> messageRepository.setSecretCopyRecipient(a.getAccountId(), email.getMessageId());

            addToEmail.accept(account);

            accountStream(messageForm.getRecipients()).forEach(setRecipient);

            accountStream(messageForm.getCopyRecipients()).forEach(setCopyRecipient);

            accountStream(messageForm.getSecretCopyRecipients()).forEach(setSecretCopyRecipient);
        };
    }

    @Override
    public List<Message> findDraftsOf(Account account) {
        return messageRepository.findAllBySenderAndStatus(account.getAccountId(), "DRAFT");
    }

    @Override
    public Optional<Message> find(long messageId) {
        return messageRepository.findById(messageId);
    }

    @Override
    public List<Message> findBelongingToAccount(Account account) {
        return messageRepository.findAllByAccount(account.getAccountId());
    }

    @Override
    public Predicate<Message> can(Account account) {
        return message -> messageRepository.accountHasMessage(account.getAccountId(), message.getMessageId());
    }

    @Override
    public Consumer<Account> delete(Long messageId) {
        return account -> messageRepository.deleteMessageFromAccount(account.getAccountId(), messageId);
    }

    @Override
    public List<Message> findSentBy(Account account) {
        List<Message> allBySender = messageRepository.findAllBySender(account.getAccountId());
        allBySender.forEach(m -> m.setSender(account));
        return allBySender;
    }

    @Override
    public Search search(Account account) {
        List<Message> messages = findBelongingToAccount(account);
        return new SimpleSearch(messages.stream());
    }

    private Message toMessage(MessageForm messageForm) {
        Message email = new Message();
        email.setSubject(messageForm.getSubject());
        email.setContent(messageForm.getContent());
        return email;
    }

    private Stream<Account> accountStream(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            return Stream.empty();
        }
        return emails.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(accountRepository::findByEmail)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}


class SimpleSearch implements MessageService.Search {
    private final Stream<Message> messages;

    SimpleSearch(Stream<Message> messages) {
        this.messages = messages;
    }

    @Override
    public MessageService.Search to(final String to) {
        if (to.trim().isEmpty()) return this;
        return new SimpleSearch(
                messages.filter(
                        m -> m.getRecipientsSummary().toLowerCase()
                                .contains(to.toLowerCase().trim())
                )
        );
    }

    @Override
    public MessageService.Search from(final String from) {
        if (from.trim().isEmpty()) return this;
        return new SimpleSearch(
                messages.filter(
                        m -> m.getSender().getEmail().toLowerCase()
                                .contains(from.toLowerCase().trim())
                )
        );
    }

    @Override
    public MessageService.Search subject(final String subject) {
        if (subject.trim().isEmpty()) return this;
        return new SimpleSearch(
                messages.filter(
                        m -> m.getSubject().toLowerCase()
                                .contains(subject.toLowerCase().trim())
                )
        );
    }

    @Override
    public MessageService.Search content(final String content) {
        if (content.trim().isEmpty()) return this;
        return new SimpleSearch(
                messages.filter(
                        m -> m.getContent().toLowerCase()
                                .contains(content.toLowerCase().trim())
                )
        );
    }

    @Override
    public Stream<Message> get() {
        return messages;
    }
}
