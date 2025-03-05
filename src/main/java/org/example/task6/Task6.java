package org.example.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Task6 {
    public static void main(String[] args) throws InterruptedException {
        task6();
    }
    public static void task6() throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread addThread = new Thread(new AddToList(list));
        Thread removeThread = new Thread(new RemoveFromList(list));

        addThread.start();
        removeThread.start();

        addThread.join();
        removeThread.join();
        System.out.println(list);
        System.out.println("Операции завершены.");
    }

    static class AddToList implements Runnable {
        private final List<Integer> list;

        public AddToList(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 10000; i++) {
                list.add(rand.nextInt());
            }
        }
    }

    static class RemoveFromList implements Runnable {
        private final List<Integer> list;

        public RemoveFromList(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 10000; i++) {
                if (!list.isEmpty()) {
                    list.remove(rand.nextInt(list.size()));
                }
            }
        }
    }
}
