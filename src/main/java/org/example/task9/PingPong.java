package org.example.task9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isPing = true;

    public void ping() {
        lock.lock();
        try {
            while (!isPing) {
                condition.await();
            }
            System.out.println("ping");
            TimeUnit.SECONDS.sleep(1);
            isPing = false;
            condition.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void pong() {
        lock.lock();
        try {
            while (isPing) {
                condition.await();
            }
            System.out.println("pong");
            TimeUnit.SECONDS.sleep(1);
            isPing = true;
            condition.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PingPong game = new PingPong();

        Thread pingThread = new Thread(() -> {
            while (true) game.ping();
        });

        Thread pongThread = new Thread(() -> {
            while (true) game.pong();
        });

        pingThread.start();
        pongThread.start();
    }
}
