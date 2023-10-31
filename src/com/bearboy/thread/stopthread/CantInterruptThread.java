package com.bearboy.thread.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * while 里面用try/catch 捕获InterruptedException，导致线程无法正常终止
 */
public class CantInterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                System.out.println("start thread + "+ Thread.currentThread().getName());
                int i=0;
                while (i<100 && !Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    System.out.println("打印： + " + i);

                }
                System.out.println("end thread + " + Thread.currentThread().getName());
                System.out.println(Thread.interrupted());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        thread.interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(thread.interrupted());
    }
}
