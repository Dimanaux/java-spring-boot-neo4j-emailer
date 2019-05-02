package com.example.emailer.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"owner", "contacts"})
@NodeEntity(label = "Group")
public class Group {
    @Id
    @GeneratedValue
    private Long groupId;

    private String name;

    @Relationship(type = "OWNED_BY", direction = Relationship.UNDIRECTED)
    private Account owner;

    @Relationship(type = "CONTAINS")
    private List<Account> contacts = new LinkedList<>();
}
