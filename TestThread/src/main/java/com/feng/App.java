package com.feng;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class App {
    private static Thread t = null;
    public static void main(String[] args) throws InterruptedException {
        Test1();
        t.join();
        ////////////////////////////////////////
        Thread t1 = new MyThread();
        t1.start();
        Thread.sleep(100);
        t1.interrupt();
        t1.join();
        ////////////////////////////////////////
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
        ////////////////////////////////////////
        TaskQueue queue = new TaskQueue();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String task = queue.getTask();
                            System.out.println("execute task: " + task);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            threads.add(t);
        }

        Thread add1 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                String task = "task-" + Math.random();
                System.out.println("add task: " + task);
                queue.addTask(task);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });
        add1.start();
        add1.join();

        Thread.sleep(100);
        for (Thread t : threads) {
            t.interrupt();
        }
        ////////////////////////////////////////

        System.out.println("main thread end!");
    }

    public static void Test1() {
        t = new Thread(() -> {
            System.out.println("start new thread!");
        });
        t.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
        }
    }
}

class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            synchronized (Counter.lock) {
                Counter.count++;
            }
        }
    }
}

class DecThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            synchronized (Counter.lock) {
                Counter.count--;
            }
        }
    }
}

class TaskQueue {
    private Queue<String> queue = new LinkedList<>();
    public synchronized void addTask(String task) {
        this.queue.add(task);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }

        return queue.remove();
    }
}

//use ReentrantLock and Condition
class TaskQueue2 {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String task) {
        lock.lock();
        try {
            queue.add(task);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }
}

// https://www.liaoxuefeng.com/wiki/1252599548343744/1309138673991714
class Point {
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}