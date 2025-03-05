package org.example.task3;

// Третий поток
class Task3 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "3 класс");
    }
}
