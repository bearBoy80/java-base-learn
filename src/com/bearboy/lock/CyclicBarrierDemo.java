package com.bearboy.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 等待所有线程到达临界点后，在一起执行线程任务。支持计数重置，超时时间，线程中断
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("到达屏障点后执行动作");
        });
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //任务完成后就减1
                try {
                    if (Thread.currentThread().getId() % 2 == 0){
                        Thread.currentThread().interrupt();
                        System.out.println("执行当前线程中断+"+Thread.currentThread().getName());
                    }
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 任务执行完成");
                } catch (InterruptedException e) {
                    System.out.println("当前线程"+Thread.currentThread().getName() + ": 收到interrupt");
                } catch (BrokenBarrierException e) {
                    System.out.println("当前线程"+Thread.currentThread().getName() + ": 收到BrokenBarrierException");
                }
            });
            thread.start();
        }
        //等待一次cyclicBarrier执行完成
        Thread.sleep(2000);
        //cyclicBarrier.reset();
//        for (int i = 0; i < 6; i++) {
//            Thread thread = new Thread(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                //任务完成后就减1
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (BrokenBarrierException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(Thread.currentThread().getName() + " 任务执行完成");
//            });
//            thread.start();
//        }

    }
}
