package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.db.repositories.MessageRepository;
import com.example.emailer.forms.MessageForm;
import com.example.emailer.services.MessageService;
import com.example.emailer.services.functions.Creator;
import com.example.emailer.services.functions.MessageDestroyer;
import com.example.emailer.services.functions.MessageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Creator send(final MessageForm messageForm) {
        final Message email = toMessage(messageForm);

        return account -> {
            email.setStatus("SENT");
            messageRepository.save(email);

            messageRepository.setSender(account.getAccountId(), email.getMessageId());

            messageRepository.addMessageToAccount(account.getAccountId(), email.getMessageId());

            accountStream(messageForm.getRecipients())
                    .forEach(a -> messageRepository.addMessageToAccount(a.getAccountId(), email.getMessageId()));
            accountStream(messageForm.getRecipients())
                    .forEach(a -> messageRepository.setRecipient(a.getAccountId(), email.getMessageId()));
            //todo copies recipients
        };
    }

    @Override
    public Creator saveToDrafts(final MessageForm messageForm) {
        final Message emailMessage = toMessage(messageForm);

        return account -> {
            account.getMessages().add(emailMessage);

            emailMessage.setSender(account);
            emailMessage.setStatus("DRAFT");

            accountRepository.save(account);
            messageRepository.save(emailMessage);
        };
    }

    @Override
    public List<Message> findDraftsOf(Account account) {
        return messageRepository.findAllBySenderAndStatus(account, "DRAFT");
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
    public MessageReader can(Account account) {
        return message -> messageRepository.accountHasMessage(account.getAccountId(), message.getMessageId());
    }

    @Override
    public MessageDestroyer delete(Long messageId) {
        return account -> messageRepository.deleteMessageFromAccount(account.getAccountId(), messageId);
    }

    @Override
    public List<Message> findSentBy(Account account) {
        return messageRepository.findAllBySender(account);
    }

    private Message toMessage(MessageForm messageForm) {
        Message email = new Message();
        email.setSubject(messageForm.getSubject());
        email.setContent(messageForm.getContent());

//        accountStream(messageForm.getRecipients())
//                .forEach(a -> email.getRecipients().add(a));
//
//        accountStream(messageForm.getCopyRecipients())
//                .forEach(a -> email.getCopiesRecipients().add(a));
//
//        accountStream(messageForm.getSecretCopyRecipients())
//                .forEach(a -> email.getSecretCopiesRecipients().add(a));

        return email;
    }

    private Stream<Account> accountStream(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            return Stream.empty();
        }
        return emails.stream()
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(accountRepository::findByEmail)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}
