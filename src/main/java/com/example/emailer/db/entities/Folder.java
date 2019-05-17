package com.example.emailer.db.entities;


import com.example.emailer.db.entities.id.FolderIdStrategy;
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
public class Folder implements Comparable<Folder> {
    @Id
    @GeneratedValue(strategy = FolderIdStrategy.class)
    private Long folderId;

    @Relationship(type = "OWNED_BY")
    private Account account;

    private String name;

    @Relationship(type = "CONTAINS")
    private List<Message> messages = new LinkedList<>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Folder o) {
        return Long.compare(folderId, o.folderId);
    }
}
