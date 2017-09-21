package com.gvsu;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Task implements Runnable {

    private Counter counter;

    private Task(Counter counter) { this.counter = counter; }

    public void run() { IntStream.range(0, 1000000).forEach(i -> this.counter.increment()); }
//    public void run() { IntStream.range(0, 1000000).forEach(i -> this.counter.safeIncrement()); }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Integer numberOfThreads = Integer.parseInt(args[0]);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(numberOfThreads);


        Long before = System.currentTimeMillis();
        try {
            IntStream.range(0, numberOfThreads).forEach(n -> taskExecutor.execute(new Task(counter)));
            taskExecutor.shutdown();

            taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();
        long timeToRun = after - before;


        System.out.println("Time ran in milliseconds: " + timeToRun);
        System.out.println("Counter value: " + counter.getCounter());
    }

}
