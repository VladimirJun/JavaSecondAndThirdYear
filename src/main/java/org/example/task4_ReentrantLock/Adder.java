package org.example.task4_ReentrantLock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Adder implements Runnable {
    private final ArrayList<Integer> list;
    private final ReentrantLock lock;
    private final Random random = new Random();

    public Adder(ArrayList<Integer> list, ReentrantLock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try {
                list.add(random.nextInt(100));
            } finally {
                lock.unlock();
            }
        }
    }
}
