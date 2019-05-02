package com.example.emailer.db.entities;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"contacts", "folders", "groups", "password"})
@NodeEntity(label = "Account")
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    @Relationship(type = "IN")
    private List<Group> groups = new LinkedList<>();

    @Relationship(type = "OWNS")
    private List<Folder> folders = new LinkedList<>();

    @Relationship(type = "IN_CONTACT", direction = Relationship.UNDIRECTED)
    private List<Account> contacts = new LinkedList<>();
}
