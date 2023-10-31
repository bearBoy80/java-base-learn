package com.bearboy.thread.objectmethods;

/**
 * wait 会释放synchronized
 * thread 线程会先获取synchronized
 * thread1 再获取synchronized，然后通知thread 线程继续运行
 * 运行结果如下：
 * Thread-0获取 synchronized
 * Thread-0调用wait,释放synchronized
 * Thread-1获取 synchronized
 * Thread-1释放 synchronized
 * Thread-0再次获取synchronized
 */
public class ObjectWaitAndNotifyDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread =new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() +"获取 synchronized");
                try {
                    System.out.println(Thread.currentThread().getName() +"调用wait,释放synchronized");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() +"再次获取synchronized");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread thread1 = new Thread(()->{
            synchronized(lock){
                System.out.println(Thread.currentThread().getName() +"获取 synchronized");
                lock.notify();
                System.out.println(Thread.currentThread().getName() +"释放 synchronized");
            }
        });
        thread1.start();
    }
}
