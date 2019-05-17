package com.example.emailer.services;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Group;
import com.example.emailer.forms.GroupForm;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public interface GroupService {
    Consumer<Account> create(GroupForm group);

    Optional<Group> findById(Long id);

    Set<Account> contactsOf(Account account);

    List<Group> by(Account account);
}
