package org.example.task4_ReentrantLock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Remover implements Runnable {
    private final ArrayList<Integer> list;
    private final ReentrantLock lock;
    private final Random random = new Random();

    public Remover(ArrayList<Integer> list, ReentrantLock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try {
                if (!list.isEmpty()) {
                    list.remove(random.nextInt(list.size()));
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
