package org.example.task15;

import org.apache.commons.cli.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MultiStageTaskQueue {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("d", "developers", true, "Количество потоков-разработчиков");
        options.addOption("w", "workers", true, "Количество потоков-исполнителей");

        int developerCount = 2;
        int workerCount = 3;

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

        TaskQueue queue = new TaskQueue();
        ExecutorService executor = Executors.newCachedThreadPool();

        // Запускаем разработчиков
        for (int i = 0; i < developerCount; i++) {
            executor.execute(new Developer(queue));
        }

        // Запускаем исполнителей
        for (int i = 0; i < workerCount; i++) {
            executor.execute(new Worker(queue));
        }

        // Запускаем наблюдателя
        executor.execute(new Observer(queue));
    }
}
