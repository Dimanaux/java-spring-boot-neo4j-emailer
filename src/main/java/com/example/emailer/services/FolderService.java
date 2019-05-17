package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Folder;
import com.example.emailer.db.entities.Message;
import com.example.emailer.forms.FolderForm;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface FolderService {
    Function<Account, Folder> create(FolderForm folderForm);

    Optional<Folder> find(String folderName, Account account);

    Consumer<Message> put(Folder folder);

    List<Message> getMessages(Folder folder);
}
