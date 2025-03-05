package org.example.task14;

import java.util.Random;

class Developer implements Runnable {
    private final TaskQueue taskQueue;
    private final Random rand = new Random();

    public Developer(TaskQueue queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Executable task = (rand.nextBoolean())
                        ? new Task("Обычная задача #" + rand.nextInt(100))
                        : new ExtendedTask("Расширенная задача #" + rand.nextInt(100));

                taskQueue.add(task);
                System.out.println(Thread.currentThread().getName() + " добавил " + task);
                Thread.sleep(rand.nextInt(2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
