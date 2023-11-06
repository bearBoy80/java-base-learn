package com.bearboy.thread.jmm;

import java.util.concurrent.TimeUnit;

public class VolatileTriggerDemo {
    private volatile int x = 0;
    private boolean flag = false;

    public void writerThread() {
        try {
            Thread.sleep(1000);
            flag = true;
            x = 1;  // 修改 x 的值
            System.out.println("set x=1 end....");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readerThread() {
        int j = 0;
        while (!flag) {
            j = x;
            System.out.println("j = " +j);
        }
        System.out.println("end Value of x: " + x);
    }

    public static void main(String[] args) {
        VolatileTriggerDemo demo = new VolatileTriggerDemo();

        Thread writerThread = new Thread(demo::writerThread);
        Thread readerThread = new Thread(demo::readerThread);
        writerThread.start();
        readerThread.start();

    }
}