package com.feng;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ResourceB {
    public void f() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        synchronized(this) {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void g() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        synchronized(this) {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void h() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        synchronized(this) {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ResourceB resourceB = new ResourceB();
        new Thread() {
            @Override
            public void run() {
                resourceB.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                resourceB.g();
            }
        }.start();

        resourceB.h();
    }
}
