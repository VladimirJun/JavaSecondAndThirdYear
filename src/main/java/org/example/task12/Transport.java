package org.example.task12;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transport {
    private static final String OUTPUT_FILE = "emails_sent.txt";

    public static void send(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
            writer.write("To: " + message.getEmailAddress() + "\n");
            writer.write("From: " + message.getSender() + "\n");
            writer.write("Subject: " + message.getSubject() + "\n");
            writer.write(message.getBody() + "\n");
            writer.write("----\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}