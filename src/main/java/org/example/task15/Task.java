package org.example.task15;

import org.example.task15.Executable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Task {
    private final String name;
    private final List<Executable> stages;
    private final AtomicInteger currentStage = new AtomicInteger(0);

    public Task(String name, List<Executable> stages) {
        this.name = name;
        this.stages = stages;
    }

    public String getName() {
        return name;
    }

    public int getCurrentStage() {
        return currentStage.get();
    }

    public int getTotalStages() {
        return stages.size();
    }

    public boolean executeNextStage() {
        int stageIndex = currentStage.getAndIncrement();
        if (stageIndex < stages.size()) {
            System.out.println(Thread.currentThread().getName() + " выполняет " + name + " [Стадия " + (stageIndex + 1) + "/" + stages.size() + "]");
            stages.get(stageIndex).execute();
            return stageIndex + 1 < stages.size();
        }
        return false;
    }
}
