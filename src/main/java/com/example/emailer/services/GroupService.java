package com.example.emailer.services;

import com.example.emailer.db.entities.Group;
import com.example.emailer.forms.GroupForm;
import com.example.emailer.services.functions.Creator;

import java.util.Optional;

public interface GroupService {
    Creator create(GroupForm group);
    Optional<Group> findById(Long id);
}
