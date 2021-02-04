package com.feng;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

    }

    public int incrementAndGet(AtomicInteger var) {
        int prev, next;
        do {
            prev = var.get();
            next = prev + 1;
        } while (!var.compareAndSet(prev, next));
        return next;
    }
}
