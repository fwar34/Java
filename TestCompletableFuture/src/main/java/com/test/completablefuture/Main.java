package com.test.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Test1();
        Test2();
    }

    // CompletableFuture回调机制
    static void Test1() throws InterruptedException {
        // 创建异步任务
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(Main::fetchPrice);
        //异步任务成功的回调
        future.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 异步任务异常的回调
        future.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    // 两个CompletableFuture串行
    static void Test2() throws InterruptedException {
       CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
           return queryCode("壳牌");
       });
       CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
           return fetchPrice(code);
       });
       cfFetch.thenAccept((result) -> {
           System.out.println("price: " + result);
       });
       Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        return "666666";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        return 5 + Math.random() * 20;
    }

    // 两个CompletableFuture并行
    static void Test3() {

    }
}
