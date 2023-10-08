package com.bearboy.thread.startThread;

/**
 * 启动线程正确的做法是调用start 方法
 */
public class StartAndRunThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " StartAndRunThread run....");
    }

    public static void main(String[] args) {
        Thread thread = new StartAndRunThread();
        thread.start();
        //直接调用run方法会导致执行线程是main
        thread.run();
    }
}
