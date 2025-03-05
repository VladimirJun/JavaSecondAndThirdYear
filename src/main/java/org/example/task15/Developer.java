package org.example.task15;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
class Developer implements Runnable {
    private final TaskQueue taskQueue;
    private final Random rand = new Random();
    private static final String[] TASK_NAMES = {"Обработка данных", "Рендеринг", "Запрос в БД"};

    public Developer(TaskQueue queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String taskName = TASK_NAMES[rand.nextInt(TASK_NAMES.length)] + " #" + rand.nextInt(100);
                List<Executable> stages = Arrays.asList(
                        () -> System.out.println("Стадия 1: Инициализация"),
                        () -> System.out.println("Стадия 2: Выполнение"),
                        () -> System.out.println("Стадия 3: Завершение")
                );
                Task task = new Task(taskName, stages);
                taskQueue.add(task);
                System.out.println(Thread.currentThread().getName() + " добавил " + task.getName());
                Thread.sleep(rand.nextInt(3000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
