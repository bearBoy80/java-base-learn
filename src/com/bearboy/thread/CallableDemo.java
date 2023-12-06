package com.bearboy.thread;

import java.util.concurrent.*;

/**
 * Callable类是对runnable类的补充，runnable类不支持返回值、不支持抛出异常
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(1);
        Callable<String> callable = new CallableTask();
        Future<String> future = executors.submit(callable);
        try {
            //阻塞当前线程
            String result = future.get();
            System.out.println("获取到子线程的结果:" + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("子线程执行异常");
        }
        executors.shutdown();
    }
}

class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        System.out.println("执行call任务结束");
        return "call end";
    }
}
