package org.example.task2;

public class Task2 {
    public static void waitForFinishInPrevious() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Working");
        });
        thread.start();
        thread.join();
        System.out.println("Finished");
    }
    public static void main(String[] args) throws InterruptedException {
        waitForFinishInPrevious();
    }
}
