package org.example.task12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sender {
    private static final int THREAD_COUNT = 5; // Количество потоков

    public static void main(String[] args) {
        String emailFile = "C:\\Users\\vovab\\IdeaProjects\\laab2\\src\\main\\java\\org\\example\\task12\\email.txt";
        String sender = "admin@example.com";
        String subject = "Important Update";
        String body = "If you read this message, I have already did my lab!";

        try {
            List<String> emailAddresses = Files.readAllLines(Paths.get(emailFile), StandardCharsets.UTF_8);
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            for (String email : emailAddresses) {
                Message message = new Message(email, sender, subject, body);
                executor.execute(() -> Transport.send(message));
            }

            executor.shutdown();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}