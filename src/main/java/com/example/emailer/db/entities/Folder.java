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
@ToString(exclude = {"account", "messages"})
@NodeEntity(label = "Folder")
public class Folder {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "OWNED_BY")
    private Account account;

    private String name;

    @Relationship(type = "CONTAINS")
    private List<Message> messages = new LinkedList<>();
}
