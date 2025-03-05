package org.example.task3;

// Первый поток
class Task1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-первый класс");
    }
}
