package com.bearboy.thread.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * 正确的处理interrupt异常
 */
public class RightWayThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable= () -> {
            System.out.println("start thread + "+ Thread.currentThread().getName());
            int i=0;
            try {
                while (i<100){
                    Thread.sleep(10);
                    i++;
                    System.out.println("打印： + " + i);
                }
            System.out.println("end thread + " + Thread.currentThread().getName());
            }catch (Exception e){
                //线程中断标志位会被清理
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.interrupted());
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}
