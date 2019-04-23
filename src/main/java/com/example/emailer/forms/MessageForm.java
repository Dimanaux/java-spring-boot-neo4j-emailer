package com.example.emailer.forms;

import java.util.List;

public class MessageForm {
    private String subject;
    private String content;
    private List<String> recipients;
    private List<String> copyRecipients;
    private List<String> secretCopyRecipients;

    public MessageForm() {
    }

    public String getSubject() {
        return subject;
    }

    public MessageForm setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageForm setContent(String content) {
        this.content = content;
        return this;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public MessageForm setRecipients(List<String> recipients) {
        this.recipients = recipients;
        return this;
    }

    public List<String> getCopyRecipients() {
        return copyRecipients;
    }

    public MessageForm setCopyRecipients(List<String> copyRecipients) {
        this.copyRecipients = copyRecipients;
        return this;
    }

    public List<String> getSecretCopyRecipients() {
        return secretCopyRecipients;
    }

    public MessageForm setSecretCopyRecipients(List<String> secretCopyRecipients) {
        this.secretCopyRecipients = secretCopyRecipients;
        return this;
    }
}
