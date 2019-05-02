package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.db.repositories.MessageRepository;
import com.example.emailer.forms.MessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Message> findAllAvailableTo(Account account) {
        return messageRepository.findAllAvailableToAccount(account.getEmail());
    }

    @Override
    public MessageSender send(MessageForm messageForm) {
        final Message emailMessage = toMessage(messageForm);

        return account -> {
            emailMessage.setSender(account);
            emailMessage.setStatus("SENT");
            messageRepository.save(emailMessage);
        };
    }

    @Override
    public MessageSender saveToDrafts(MessageForm messageForm) {
        final Message emailMessage = toMessage(messageForm);

        return account -> {
            emailMessage.setSender(account);
            emailMessage.setStatus("DRAFT");
            messageRepository.save(emailMessage);
        };
    }

    private Message toMessage(MessageForm messageForm) {
        Message email = new Message() {{
            setSubject(messageForm.getSubject());
            setContent(messageForm.getContent());
        }};
        messageForm.getRecipients().stream()
                .map(accountRepository::findByEmail)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(a -> email.getRecipients().add(a));
        return email;
    }
}
