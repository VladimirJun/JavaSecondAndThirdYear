package org.example.task13;

import org.apache.commons.cli.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataApp {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("p", "producers", true, "Количество потоков-писателей");
        options.addOption("c", "consumers", true, "Количество потоков-читателей");

        CommandLineParser parser = new DefaultParser();
        int producerCount = 2;
        int consumerCount = 2;

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("p")) {
                producerCount = Integer.parseInt(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("c")) {
                consumerCount = Integer.parseInt(cmd.getOptionValue("c"));
            }
        } catch (ParseException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }

        DataQueue queue = new DataQueue();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < producerCount; i++) {
            executor.execute(new Producer(queue));
        }

        for (int i = 0; i < consumerCount; i++) {
            executor.execute(new Consumer(queue));
        }
    }
}
