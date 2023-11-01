package com.bearboy.thread;

import java.util.concurrent.TimeUnit;

/**
 * thread 异常处理：主线程无法捕获到子线程异常，可以通过UncaughtExceptionHandler来实现线程异常处理
 */
public class ThreadExceptionHandlerDemo implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException("thread error");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((Thread thread,Throwable e)->{
            System.out.println("获取子线程异常信息:" + e.getMessage());
        });
        Thread thread = new Thread(new ThreadExceptionHandlerDemo());
        thread.setUncaughtExceptionHandler((Thread thread1,Throwable e)->{
            System.out.println("Thread 获取子线程异常信息:" + e.getMessage());
        });
        //运行这个代码会导致堆栈溢出
        //Thread.setDefaultUncaughtExceptionHandler(thread.getThreadGroup());
        thread.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
