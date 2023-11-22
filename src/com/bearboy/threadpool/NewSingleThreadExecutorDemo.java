package com.bearboy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newSingleThreadExecutor 默认是有一个线程，具体是 FinalizableDelegatedExecutorService
 * core size = maxsize = 1
 * 默认队列：LinkedBlockingQueue，默认队列长度是Integer.MAX_VALUE，这个可能会出现oom
 */
public class NewSingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor =
                Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++){
            executor.submit(new Task());
        }
        executor.shutdown();
    }
}
class Task implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

