package com.example.emailer.db.entities;

import com.example.emailer.db.entities.id.MessageIdStrategy;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

@NodeEntity(label = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = MessageIdStrategy.class)
    private Long messageId;

    @Relationship(type = "SENT_BY", direction = Relationship.UNDIRECTED)
    private Account sender;

    private String senderEmail;

    private String status;

    private String subject;

    private String content;

    @DateString("yyyy-MM-dd'T'HH:mm:ss")
    private Date sentAt;

    @Relationship(type = "SENT_TO")
    private List<Account> recipients = new LinkedList<>();

    @Relationship(type = "COPY_TO")
    private List<Account> copiesRecipients = new LinkedList<>();

    @Relationship(type = "SECRET_COPY_TO")
    private List<Account> secretCopiesRecipients = new LinkedList<>();

    @Relationship(type = "CONTAINS")
    private List<String> attachments = new LinkedList<>();

    public Message() {
        sentAt = new Date();
    }

    public String getRecipientsSummary() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        recipients.stream().map(Account::getEmail).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    public void setSender(Account account) {
        sender = account;
        senderEmail = account.getEmail();
    }

    public Account getSender() {
        return sender;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getStatus() {
        return status;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public List<Account> getRecipients() {
        return recipients;
    }

    public List<Account> getCopiesRecipients() {
        return copiesRecipients;
    }

    public List<Account> getSecretCopiesRecipients() {
        return secretCopiesRecipients;
    }

    public Message setStatus(String status) {
        this.status = status;
        return this;
    }

    public Message setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Message setSentAt(Date sentAt) {
        this.sentAt = sentAt;
        return this;
    }

    public Long getMessageId() {
        return messageId;
    }
}
