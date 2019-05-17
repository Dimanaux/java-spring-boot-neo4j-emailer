package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Group;
import com.example.emailer.db.repositories.GroupRepository;
import com.example.emailer.forms.GroupForm;
import com.example.emailer.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Consumer<Account> create(GroupForm form) {
        Group group = new Group();
        group.setName(form.getName());

        return account -> {
            groupRepository.save(group);

            groupRepository.setOwner(group.getGroupId(), account.getAccountId());
            groupRepository.bindAccountToGroup(group.getGroupId(), account.getAccountId());

            group.setOwner(account);
            group.getContacts().add(account);
        };
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Set<Account> contactsOf(Account account) {
        Set<Account> contacts = groupRepository.groupsOf(account.getAccountId()).stream()
                .map(Group::getContacts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return contacts;
    }

    @Override
    public List<Group> by(Account account) {
        return groupRepository.groupsOf(account.getAccountId());
    }
}
