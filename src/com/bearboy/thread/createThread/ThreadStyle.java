package com.bearboy.thread.createThread;

/**
 * 通过继承thread类来实现创建线程
 */
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("ThreadStyle run");
    }

    public static void main(String[] args) {
        Thread thread = new ThreadStyle();
        thread.start();
    }
}
