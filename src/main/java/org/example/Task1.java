package org.example;


public class Task1 {
    public static void getAttributes(){
        Thread currentThread = Thread.currentThread();
        System.out.println("Имя потока: " + currentThread.getName());
        System.out.println("Идентификатор потока: " + currentThread.threadId());
        System.out.println("Приоритет потока: " + currentThread.getPriority());
        System.out.println("Поток жив: " + currentThread.isAlive());
        System.out.println("Поток в ожидании: " + currentThread.isDaemon());
        System.out.println("Поток прерван: " + currentThread.isInterrupted());
        System.out.println("Имя группы потока: " + currentThread.getThreadGroup());
    }
    public static void main(String[] args) {
        getAttributes();
    }

}
