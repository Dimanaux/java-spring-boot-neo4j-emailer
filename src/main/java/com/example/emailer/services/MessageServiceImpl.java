package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.db.repositories.MessageRepository;
import com.example.emailer.forms.MessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public Message toMessage(MessageForm messageForm) {
        Message message = new Message() {{
            setSubject(messageForm.getSubject());
            setContent(messageForm.getContent());
        }};
        message.setRecipients(Collections.emptyList());
        messageForm.getRecipients().stream()
                .map(accountRepository::findByEmail)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(a -> message.getRecipients().add(a));
        return message;
    }

    @Override
    public void send(Message message) {
        message.setStatus("SENT");
        messageRepository.save(message);
    }

    @Override
    public void saveToDrafts(Message draft) {
        draft.setStatus("DRAFT");
        messageRepository.save(draft);
    }
}
