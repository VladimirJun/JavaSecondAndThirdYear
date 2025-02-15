package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Task4 {
    public static void main(String[] args) throws InterruptedException {
        task4();
    }
    public static void task4() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Thread addThread = new Thread(new AddToList(list));
        Thread removeThread = new Thread(new RemoveFromList(list));

        addThread.start();
        removeThread.start();

        addThread.join();
        removeThread.join();
        System.out.println(list);
        System.out.println("finished");
    }

    static class AddToList implements Runnable {
        private final ArrayList<Integer> list;

        public AddToList(ArrayList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 100; i++) {
                synchronized (list) {
                    list.add(rand.nextInt());
                }
            }
        }
    }

    static class RemoveFromList implements Runnable {
        private final ArrayList<Integer> list;

        public RemoveFromList(ArrayList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 100; i++) {
                synchronized (list) {
                    if (!list.isEmpty()) {
                        list.remove(rand.nextInt(list.size()));
                    }
                }
            }
        }
    }
}
