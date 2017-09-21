package com.gvsu;

public class Counter {

    private int counter = 0;

    public Counter() {}

    public int getCounter() { return counter; }

    void increment() { ++this.counter; }

    synchronized void safeIncrement() { ++this.counter; }
}
