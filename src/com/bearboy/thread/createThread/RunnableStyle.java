package com.bearboy.thread.createThread;

/**
 * 通过runnable 方式创建线程
 */
public class RunnableStyle implements Runnable{
    @Override
    public void run() {
        System.out.println("runnable run.....");
    }

    public static void main(String[] args) {

        new Thread(new RunnableStyle()).start();
    }
}
