package org.example.task13;

import java.util.Arrays;

class Consumer implements Runnable {
    private final DataQueue queue;

    public Consumer(DataQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Data data = queue.take();
                System.out.println(Thread.currentThread().getName() + " прочитал: " + Arrays.toString(data.get()));
                Thread.sleep(1000); // Имитация обработки
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

