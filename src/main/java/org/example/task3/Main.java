package org.example.task3;

    public class Main {
        public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new Thread(new Task1());
            Thread thread2 = new Thread(new Task2());
            Thread thread3 = new Thread(new Task3());

            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();

            System.out.println("Все потоки завершены.");
        }

}
