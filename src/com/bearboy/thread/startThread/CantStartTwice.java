package com.bearboy.thread.startThread;

public class CantStartTwice implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" CantStartTwice run....");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new CantStartTwice());
        thread.start();
        //第二次执行会报IllegalThreadStateException
        thread.start();
    }
}
