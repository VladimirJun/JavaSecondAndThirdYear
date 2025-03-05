package org.example.task15;

import java.util.List;

class Observer implements Runnable {
    private final TaskQueue taskQueue;

    public Observer(TaskQueue queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<Task> tasks = taskQueue.getCurrentTasks();
                if (!tasks.isEmpty()) {
                    System.out.println("=== Текущие задачи в очереди ===");
                    for (Task task : tasks) {
                        System.out.println(task.getName() + " - Стадия " + task.getCurrentStage() + "/" + task.getTotalStages());
                    }
                }
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
