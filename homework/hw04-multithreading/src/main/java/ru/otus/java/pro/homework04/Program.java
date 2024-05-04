package ru.otus.java.pro.homework04;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        var threadPool = new CustomThreadPool(3);
        for (int i = 0; i < 10; i++) {
            var counter = i;
            threadPool.execute(() -> System.out.printf("Task %s -> thread %s\n", counter, Thread.currentThread().getName()));
        }

        Thread.sleep(100);
        threadPool.shutDown();

        threadPool.execute(() -> System.out.printf("Task %s -> thread %s\n", 10, Thread.currentThread().getName()));
    }
}
