package org.example.task13;

import java.util.Arrays;

class Producer implements Runnable {
    private final DataQueue queue;

    public Producer(DataQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Data data = new Data();
                queue.put(data);
                System.out.println(Thread.currentThread().getName() + " записал: " + Arrays.toString(data.get()));
                Thread.sleep(500); // Имитация работы
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

