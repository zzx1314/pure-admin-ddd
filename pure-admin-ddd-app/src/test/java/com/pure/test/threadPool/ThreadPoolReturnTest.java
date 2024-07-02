package com.pure.test.threadPool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadPoolReturnTest {
    @Test
    public void testThreadPool() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "futureTask1 执行完成";
            }
        });

        new Thread(futureTask1).start();
        System.out.println(futureTask1.get());

        System.out.println("TODO.....");
    }

    @Test
    public void testThreadPoolV1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> futureTask1 = CompletableFuture.runAsync(()-> System.out.println("执行没有返回值的任务"));

        CompletableFuture<String> futureTask2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "有返回值的任务";
        });
        System.out.println(futureTask2.join());

        System.out.println("TODO...");
    }

    @Test
    public void testThreadPoolV2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureTask1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "有返回值的任务1";
        });

        CompletableFuture<String> futureTask2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "有返回值的任务2";
        });
        // 返回最快的
        CompletableFuture<Object> all = CompletableFuture.anyOf(futureTask2, futureTask1);

       /* CompletableFuture<String> all = CompletableFuture.allOf(futureTask2, futureTask1).thenApply(one ->{
            return futureTask2.join()+ futureTask1.join();
        });*/
        System.out.println(all.join());

        System.out.println("TODO...");
    }
}
