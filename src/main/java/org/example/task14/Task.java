package org.example.task14;

import java.util.Random;

class Task implements Executable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " выполняет задачу: " + name);
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " завершил задачу: " + name);
    }
}

