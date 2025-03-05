package org.example.task13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class DataQueue {
    private final BlockingQueue<Data> queue = new LinkedBlockingQueue<>(10); // Ограничение очереди

    public void put(Data data) throws InterruptedException {
        queue.put(data);
    }

    public Data take() throws InterruptedException {
        return queue.take();
    }
}

