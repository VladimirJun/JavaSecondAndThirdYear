package org.example.task4_ReentrantLock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();

        Thread adder = new Thread(new Adder(list, lock));
        Thread remover = new Thread(new Remover(list, lock));

        adder.start();
        remover.start();

        adder.join();
        remover.join();

        System.out.println("Размер списка: " + list.size());
    }
}

