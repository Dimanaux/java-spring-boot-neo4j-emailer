package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Group;
import com.example.emailer.db.repositories.GroupRepository;
import com.example.emailer.forms.GroupForm;
import com.example.emailer.services.GroupService;
import com.example.emailer.services.functions.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Creator create(GroupForm form) {
        Group group = new Group();
        group.setName(form.getName());

        return account -> {
            group.setOwner(account);
            group.getContacts().add(account);
            groupRepository.save(group);
        };
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }
}
