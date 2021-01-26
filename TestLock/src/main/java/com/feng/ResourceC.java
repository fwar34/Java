package com.feng;

import java.util.concurrent.TimeUnit;

public class ResourceC {
    private Object syncObject1 = new Object();
    private Object syncObject2 = new Object();

    public void f() {
        System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
        synchronized (this) {
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
        synchronized (syncObject1) {
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
        synchronized (syncObject2) {
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
        ResourceC resourceC = new ResourceC();
        new Thread() {
            @Override
            public void run() {
                resourceC.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                resourceC.g();
            }
        }.start();

        resourceC.h();
    }
}
