package org.example.task14;

class Worker implements Runnable {
    private final TaskQueue taskQueue;

    public Worker(TaskQueue queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Executable task = taskQueue.take();
                task.execute();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
