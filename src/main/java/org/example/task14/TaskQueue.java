package org.example.task14;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class TaskQueue {
    private final BlockingQueue<Executable> queue = new LinkedBlockingQueue<>();

    public void add(Executable task) throws InterruptedException {
        queue.put(task);
    }

    public Executable take() throws InterruptedException {
        return queue.take();
    }
}
