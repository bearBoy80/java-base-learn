package com.bearboy.thread.createThread;

/**
 * 面试题：如果同时实现Runnable的run方法、重写Thread的run方法会发生什么结果？
 */
public class BothRunThread {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("Runnable接口 run");
        }){
            @Override
            public void run() {
                System.out.println("Thread类 run");
            }
        }.start();
    }
}
