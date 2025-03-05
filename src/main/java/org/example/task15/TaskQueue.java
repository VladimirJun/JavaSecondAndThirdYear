package org.example.task15;

import org.example.task15.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class TaskQueue {
    private final BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    public void add(Task task) throws InterruptedException {
        queue.put(task);
    }

    public Task take() throws InterruptedException {
        return queue.take();
    }

    public List<Task> getCurrentTasks() {
        return new ArrayList<>(queue);
    }
}

