package com.bearboy.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 创建6个线程去执行任务，每个线程执行任务完成后，都进行计数器减1，直到减到0后，所有任务都运行完成
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i=0;i <6;i++){
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //任务完成后就减1
                latch.countDown();
                System.out.println(Thread.currentThread().getName() +" 任务执行完成");
            });
            thread.start();
        }
        //等待所有任务都完成
        System.out.println(Thread.currentThread().getName() +" 等待任务执行完成");
        latch.await();
        System.out.println(Thread.currentThread().getName() +" 结束任务");


    }
}
