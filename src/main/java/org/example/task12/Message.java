package org.example.task12;

import java.util.Objects;

public class Message {
    private final String emailAddress;
    private final String sender;
    private final String subject;
    private final String body;

    public Message(String emailAddress, String sender, String subject, String body) {
        this.emailAddress = emailAddress;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(emailAddress, message.emailAddress) && Objects.equals(sender, message.sender) && Objects.equals(subject, message.subject) && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, sender, subject, body);
    }
}
