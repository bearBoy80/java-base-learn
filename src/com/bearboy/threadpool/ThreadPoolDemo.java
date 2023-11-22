package com.bearboy.threadpool;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * 创建一个maxsize=4，coresize=2 队列为有界队列，最多接受14个task，超过14个后就会拒绝执行
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        try {
            submitWithCallable(executor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
    public static void execute(ThreadPoolExecutor executor){
        for (int i = 0; i < 140; i++) {
           executor.execute(new Task());
        }
    }
    public static void submitWithRunnable(ThreadPoolExecutor executor){
        for (int i = 0; i < 140; i++) {
            Future future = executor.submit(new Task());
            //输出为false
            System.out.println(future.isDone());
        }
    }
    public static void submitWithCallable(ThreadPoolExecutor executor) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 140; i++) {
            Future future = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println("线程："+Thread.currentThread().getName()+ "执行任务结束");
                    return Thread.currentThread().getId();
                }
            });
            //future.get会阻塞当前运行线程
            System.out.println("线程："+Thread.currentThread().getName()+ "执行结果：" + future.get() + LocalDateTime.now());
        }
    }
}
