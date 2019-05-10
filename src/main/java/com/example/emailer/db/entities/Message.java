package com.example.emailer.db.entities;

import com.example.emailer.db.entities.id.MessageIdStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.*;

@Data
@AllArgsConstructor
@ToString(exclude = {"sender", "recipients", "copiesRecipients", "secretCopiesRecipients", "attachments"})
@NodeEntity(label = "Message")
public class Message implements Comparable<Message> {
    @Id
    @GeneratedValue(strategy = MessageIdStrategy.class)
    private Long messageId;

    @Relationship(type = "SENT_BY")
    private Account sender;

    private String status;

    private String subject;

    private String content;

    @DateString("yyyy-MM-dd'T'HH:mm:ss")
    private Date sentAt;

    @Relationship(type = "SENT_TO")
    private Set<Account> recipients = new TreeSet<>();

    @Relationship(type = "COPIED_TO")
    private Set<Account> copiesRecipients = new TreeSet<>();

    @Relationship(type = "SECRETLY_COPIED_TO")
    private Set<Account> secretCopiesRecipients = new TreeSet<>();

    @Relationship(type = "HAS")
    private Set<String> attachments = new TreeSet<>();

    public Message() {
        sentAt = new Date();
    }

    public String getRecipientsSummary() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        recipients.stream().map(Account::getEmail).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    @Override
    public int compareTo(Message o) {
        return Long.compare(messageId, o.messageId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) &&
                Objects.equals(status, message.status) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(content, message.content) &&
                Objects.equals(sentAt, message.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, status, subject, content);
    }
}
