package com.example.emailer.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sender", "recipients", "copiesRecipients", "secretCopiesRecipients", "attachments"})
@NodeEntity(label = "Message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "SENT_BY")
    private Account sender;

    private String status;

    private String subject;

    private String content;

    @DateString
    private LocalDateTime sentAt;

    @Relationship(type = "SENT_TO")
    private List<Account> recipients = new LinkedList<>();

    @Relationship(type = "COPY_TO")
    private List<Account> copiesRecipients = new LinkedList<>();

    @Relationship(type = "SECRET_COPY_TO")
    private List<Account> secretCopiesRecipients = new LinkedList<>();

    @Relationship(type = "CONTAINS")
    private List<String> attachments = new LinkedList<>();
}
