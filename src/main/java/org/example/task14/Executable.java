package org.example.task14;

import org.apache.commons.cli.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface Executable {
    void execute();
    class TaskQueueSystem {
        public static void main(String[] args) {
            Options options = new Options();
            options.addOption("d", "developers", true, "Количество потоков-разработчиков");
            options.addOption("w", "workers", true, "Количество потоков-исполнителей");

            int developerCount = Runtime.getRuntime().availableProcessors() / 2; // Автоматическое значение
            int workerCount = Runtime.getRuntime().availableProcessors(); // Автоматическое значение

            try {
                CommandLineParser parser = new DefaultParser();
                CommandLine cmd = parser.parse(options, args);
                if (cmd.hasOption("d")) {
                    developerCount = Integer.parseInt(cmd.getOptionValue("d"));
                }
                if (cmd.hasOption("w")) {
                    workerCount = Integer.parseInt(cmd.getOptionValue("w"));
                }
            } catch (ParseException e) {
                System.err.println("Ошибка парсинга аргументов. Используем значения по умолчанию.");
            }

            System.out.println("Запуск системы с " + developerCount + " разработчиками и " + workerCount + " исполнителями.");

            TaskQueue queue = new TaskQueue();
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < developerCount; i++) {
                executor.execute(new Developer(queue));
            }
            for (int i = 0; i < workerCount; i++) {
                executor.execute(new Worker(queue));
            }
        }
    }
}
