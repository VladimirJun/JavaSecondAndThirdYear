package org.example;

public class Task3 {
    public static void main(String[] args) {

    }
    public void task3() throws InterruptedException {
        Thread thread1 = new Thread((new MyRunnable()));
        Thread thread2 = new Thread((new MyRunnable()));
        Thread thread3 = new Thread((new MyRunnable()));
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

public static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Thread"+Thread.currentThread().getName()+" is running");
        }
}
}
