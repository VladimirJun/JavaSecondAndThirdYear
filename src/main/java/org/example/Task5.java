package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Task5 {
    public static void main(String[] args) throws InterruptedException {
        task5();
    }
        public static void task5() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        ListTask listTask = new ListTask(list);

        Thread addThread = new Thread(listTask::addRandom);
        Thread removeThread = new Thread(listTask::removeRandom);

        addThread.start();
        removeThread.start();

        addThread.join();
        removeThread.join();
        System.out.println(list);
        System.out.println("finished");
    }

    static class ListTask {
        private final ArrayList<Integer> list;
        private final Random rand = new Random();

        public ListTask(ArrayList<Integer> list) {
            this.list = list;
        }

        public synchronized void addRandom() {
            for (int i = 0; i < 100; i++) {
                list.add(rand.nextInt());
            }
        }

        public synchronized void removeRandom() {
            for (int i = 0; i < 100; i++) {
                if (!list.isEmpty()) {
                    list.remove(rand.nextInt(list.size()));
                }
            }
        }
    }
}
