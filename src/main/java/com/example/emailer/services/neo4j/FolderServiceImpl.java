package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Folder;
import com.example.emailer.db.entities.Message;
import com.example.emailer.db.repositories.FolderRepository;
import com.example.emailer.db.repositories.MessageRepository;
import com.example.emailer.forms.FolderForm;
import com.example.emailer.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, MessageRepository messageRepository) {
        this.folderRepository = folderRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Function<Account, Folder> create(FolderForm folderForm) {
        Folder folder = new Folder(folderForm.getName());

        return account -> {
            folderRepository.save(folder);

            folder.setAccount(account);
            account.getFolders().add(folder);

            folderRepository.bindToAccount(account.getAccountId(), folder.getFolderId());

            return folder;
        };
    }

    @Override
    public Optional<Folder> find(String folderName, Account account) {
        Optional<Folder> folder = folderRepository.findByAccountAndName(account.getAccountId(), folderName);
        if (folder.isPresent()) return folder;
        return folderRepository.findEmptyByAccountAndName(account.getAccountId(), folderName);
    }

    @Override
    public Consumer<Message> put(Folder folder) {
        return message -> {
            folderRepository.bindFolderAndMessage(folder.getFolderId(), message.getMessageId());
            folder.getMessages().add(message);
        };
    }

    @Override
    public List<Message> getMessages(Folder folder) {
        return messageRepository.findByFolder(folder.getFolderId());
    }
}
