package com.bearboy.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 AtomicMarkableReference是一个封装了一个Object引用和一个boolean标志的泛型类。这两个字段被耦合在一起,可以**原子方式一起或单独更新**。
 AtomicMarkableReference也可能是对ABA问题的一种补救。
 */
public class AtomicMarkableReferenceDemo {
    private static AtomicMarkableReference<String> reference = new AtomicMarkableReference<>("initial value", false);

    public static synchronized void main(String[] args) {
        // 创建两个线程，一个线程进行引用的修改，另一个线程进行检测
        Thread modifyThread = new Thread(() -> {
            // 修改引用为 "new value"，标记为 true
            reference.set("new value", true);
            System.out.println("Reference updated to 'new value'");
        });
        Thread checkThread = new Thread(() -> {
            // 等待修改线程完成引用的修改
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 尝试将引用修改回 "initial value"，标记为 false
            boolean[] markHolder = new boolean[1];
            String currentValue = reference.get(markHolder);
            if (currentValue.equals("new value") && markHolder[0]) {
                // 如果引用为 "new value"，且标记为 true，则将引用修改为 "initial value"，标记为 false
                reference.compareAndSet("new value", "initial value", true, false);
                System.out.println("Reference changed back to 'initial value'");
            }
        });

        modifyThread.start();
        checkThread.start();

        try {
            modifyThread.join();
            checkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
