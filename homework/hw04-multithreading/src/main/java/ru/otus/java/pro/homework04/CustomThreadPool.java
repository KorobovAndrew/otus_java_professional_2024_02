package ru.otus.java.pro.homework04;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool {
    private final LinkedList<Runnable> tasks;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public CustomThreadPool(int threadCount) {
        tasks = new LinkedList<>();

        startThreads(threadCount);
    }

    public void execute(Runnable r) {
        if (isShutdown.get()) {
            throw new IllegalStateException("Thread pool already is shut down");
        }
        tasks.add(r);
    }

    public void shutDown() {
        isShutdown.set(true);
    }

    private void startThreads(int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            var thread = new Thread(() -> {
                while (!isShutdown.get()) {

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    getNextTask().run();
                }
            });
            thread.start();
        }
    }

    private synchronized Runnable getNextTask() {
        return Optional.ofNullable(tasks.poll()).orElse(() -> {
        });
    }
}
