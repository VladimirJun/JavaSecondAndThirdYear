package org.example.task3;

// Второй поток
class Task2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " второй класс");
    }
}
