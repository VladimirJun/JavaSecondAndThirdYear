package org.example.task7;

import java.util.concurrent.TimeUnit;

public class Task7 {
    private static final Object lock = new Object();
    private static boolean isPing = true;

    public static void main(String[] args) throws InterruptedException {
            Thread pingThread = new Thread(Task7::ping);
            Thread pongThread = new Thread(Task7::pong);

        pingThread.start();
        pongThread.start();

        pingThread.join();
        pongThread.join();
    }

    private static void ping(){
        while (true) {
            synchronized (lock) {
                if (isPing) {
                    try {
                        System.out.println("ping");
                        TimeUnit.SECONDS.sleep(1);
                        isPing = false;
                        lock.notify();
                    }catch (InterruptedException e){
                        System.out.println("\n");
                    }
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private static void pong(){
        while (true) {
            synchronized (lock) {
                if (!isPing) {
                    try {
                        System.out.println("pong");
                        TimeUnit.SECONDS.sleep(1);
                        isPing = true;
                        lock.notify();
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
