package org.example.task15;

import org.example.task15.Task;
import org.example.task15.TaskQueue;

class Worker implements Runnable {
    private final TaskQueue taskQueue;

    public Worker(TaskQueue queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = taskQueue.take();
                if (task.executeNextStage()) {
                    taskQueue.add(task); // Если есть стадии — ставим обратно
                } else {
                    System.out.println(task.getName() + " завершена!");
                }
                Thread.sleep(1000); // Имитация работы
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

