package com.feng;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceA {
    private Lock lock = new ReentrantLock();

    public void f() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        lock.lock();
        try {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void g() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        lock.lock();
        try {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void h() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        lock.lock();
        try {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ResourceA resourceA = new ResourceA();
        new Thread() {
            public void run() {
                resourceA.f();
            }
        }.start();

        new Thread() {
            public void run() {
                resourceA.g();
            }
        }.start();

        resourceA.h();
    }
}
